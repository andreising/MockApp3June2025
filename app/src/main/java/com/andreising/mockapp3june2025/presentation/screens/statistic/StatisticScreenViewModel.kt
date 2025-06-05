package com.andreising.mockapp3june2025.presentation.screens.statistic

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andreising.mockapp3june2025.domain.entity.InteractionType
import com.andreising.mockapp3june2025.domain.entity.User
import com.andreising.mockapp3june2025.domain.entity.UserInteractionHistory
import com.andreising.mockapp3june2025.domain.usecases.interations.GetUserInteractionHistoryListUseCase
import com.andreising.mockapp3june2025.domain.usecases.users.GetUsersListUseCase
import com.andreising.mockapp3june2025.presentation.utils.visitor_by_day_graph.VisitorTrendChartBuilder
import com.andreising.mockapp3june2025.presentation.utils.visitor_by_day_graph.VisitorTrendChartModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
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

    private val _userListFlow = MutableSharedFlow<List<User>>()
    val userListFlow: SharedFlow<List<User>> = _userListFlow.asSharedFlow()

    private val _interactionListFlow = MutableSharedFlow<List<UserInteractionHistory>>()
    val interactionListFlow: SharedFlow<List<UserInteractionHistory>> =
        _interactionListFlow.asSharedFlow()

    val viewedUsersCountFlow: StateFlow<List<Int>> = combine(
        userListFlow,
        interactionListFlow
    ) { users, interactions ->
        val viewedUserIds = interactions
            .filter { it.type == InteractionType.VIEW }
            .map { it.userId }
            .toSet()
        listOf(users.count { it.id in viewedUserIds })
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = emptyList()
    )

    val visitorTrendChartListFlow: StateFlow<List<VisitorTrendChartModel>> =
        interactionListFlow
            .map { list ->
                list.filter { it.type == InteractionType.VIEW }
                    .flatMap { it.dateList }
                    .groupingBy { it }
                    .eachCount()
                    .toSortedMap()
                    .map { VisitorTrendChartBuilder.getModelByDateAndInt(it.toPair()) }
            }.stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = emptyList()
            )

    val mostRecentVisitorsFlow: StateFlow<List<User>> =
        combine(userListFlow, interactionListFlow) { users, interactions ->
            val sortedUserIds = interactions
                .groupBy { it.userId }
                .mapValues { (_, userInteractions) ->
                    userInteractions.sumOf { it.dateList.size }
                }
                .toList()
                .sortedByDescending { (_, count) -> count }
                .map { (userId, _) -> userId }

            val result = mutableListOf<User>()
            val userById = users.associateBy { it.id }

            sortedUserIds.forEach { userId ->
                userById[userId]?.let { user ->
                    result.add(user)
                }
            }

            result
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    fun loadData() = viewModelScope.launch {
        launch {
            getUsersListUseCase().onSuccess {
                _userListFlow.emit(it)
            }
        }
        launch {
            getUserInteractionHistoryListUseCase().onSuccess {
                _interactionListFlow.emit(it)
            }
        }
    }
}