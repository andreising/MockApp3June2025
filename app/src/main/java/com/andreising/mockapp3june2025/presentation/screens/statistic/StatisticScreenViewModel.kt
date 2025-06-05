package com.andreising.mockapp3june2025.presentation.screens.statistic

import androidx.lifecycle.ViewModel
import com.andreising.mockapp3june2025.domain.usecases.users.GetUsersListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class StatisticScreenViewModel @Inject constructor(
    private val getUsersListUseCase: GetUsersListUseCase
): ViewModel() {

}