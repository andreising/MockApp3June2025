package com.andreising.mockapp3june2025.presentation.screens.statistic

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andreising.mockapp3june2025.domain.entity.InteractionType
import com.andreising.mockapp3june2025.domain.entity.User
import com.andreising.mockapp3june2025.domain.entity.UserInteractionHistory
import com.andreising.mockapp3june2025.domain.usecases.interations.GetUserInteractionHistoryListUseCase
import com.andreising.mockapp3june2025.domain.usecases.users.GetUsersListUseCase
import com.andreising.mockapp3june2025.presentation.utils.UiState
import com.andreising.mockapp3june2025.presentation.utils.visitor_by_day_graph.VisitorTrendChartBuilder
import com.andreising.mockapp3june2025.presentation.utils.visitor_by_day_graph.VisitorTrendChartModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StatisticScreenViewModel @Inject constructor(
    private val getUsersListUseCase: GetUsersListUseCase,
    private val getUserInteractionHistoryListUseCase: GetUserInteractionHistoryListUseCase
) : ViewModel() {

    private val _userListFlow = MutableStateFlow<List<User>>(emptyList())
    val userListFlow: StateFlow<List<User>> = _userListFlow.asStateFlow()

    private val _interactionListFlow = MutableStateFlow<List<UserInteractionHistory>>(emptyList())
    val interactionListFlow: StateFlow<List<UserInteractionHistory>> =
        _interactionListFlow.asStateFlow()

    private val _uiState = MutableStateFlow<UiState>(UiState.Loading)
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    val viewedUsersCountFlow: StateFlow<List<Int>> = combine(
        userListFlow,
        interactionListFlow
    ) { users, interactions ->
        val viewedUserIds = interactions
            .filter { it.type == InteractionType.VIEW }
            .map { it.userId }
            .toSet()
        listOf(users.count { it.id in viewedUserIds })
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val visitorTrendChartListFlow: StateFlow<List<VisitorTrendChartModel>> =
        interactionListFlow.map { list ->
            list.filter { it.type == InteractionType.VIEW }
                .flatMap { it.dateList }
                .groupingBy { it }
                .eachCount()
                .toSortedMap()
                .map { VisitorTrendChartBuilder.getModelByDateAndInt(it.toPair()) }
        }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val mostRecentVisitorsFlow: StateFlow<List<User>> =
        combine(userListFlow, interactionListFlow) { users, interactions ->
            val sortedUserIds = interactions
                .groupBy { it.userId }
                .mapValues { (_, userInteractions) ->
                    userInteractions.sumOf { it.dateList.size }
                }
                .toList()
                .sortedByDescending { it.second }
                .map { it.first }

            sortedUserIds.mapNotNull { userId -> users.find { it.id == userId } }
        }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun loadData() = viewModelScope.launch {
        _uiState.value = UiState.Loading
        try {
            val users = async { getUsersListUseCase() }
            val interactions = async { getUserInteractionHistoryListUseCase() }

            val userResult = users.await()
            val interactionResult = interactions.await()

            if (userResult.isSuccess && interactionResult.isSuccess) {
                _userListFlow.value = userResult.getOrThrow()
                _interactionListFlow.value = interactionResult.getOrThrow()
                _uiState.value = UiState.Success
            } else {
                val msg = userResult.exceptionOrNull()?.message
                    ?: interactionResult.exceptionOrNull()?.message
                    ?: "Unknown error"
                _uiState.value = UiState.Error(msg)
            }
        } catch (e: Exception) {
            _uiState.value = UiState.Error(e.localizedMessage ?: "Unexpected error")
        }
    }
}