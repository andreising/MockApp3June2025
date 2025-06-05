package com.andreising.mockapp3june2025.presentation.screens.statistic

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andreising.mockapp3june2025.domain.entity.User
import com.andreising.mockapp3june2025.domain.usecases.users.GetUsersListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StatisticScreenViewModel @Inject constructor(
    private val getUsersListUseCase: GetUsersListUseCase
): ViewModel() {

    private val _userListFlow = MutableStateFlow(emptyList<User>())
    val userListFlow = _userListFlow.asStateFlow()

    fun loadData() = viewModelScope.launch {
        getUsersListUseCase.invoke().onSuccess {
            _userListFlow.value = it
        }
    }
}