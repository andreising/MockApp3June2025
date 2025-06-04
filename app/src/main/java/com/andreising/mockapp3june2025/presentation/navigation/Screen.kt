package com.andreising.mockapp3june2025.presentation.navigation

sealed class Screen(val route: String) {
    data object StatisticScreen: Screen("statistic")
}