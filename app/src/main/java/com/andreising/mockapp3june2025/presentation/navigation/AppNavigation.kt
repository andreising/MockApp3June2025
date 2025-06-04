package com.andreising.mockapp3june2025.presentation.navigation

import androidx.compose.foundation.background
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun AppNavigation(statisticScreen: @Composable () -> Unit) {
    val navHostController = rememberNavController()

    NavHost(
        navController = navHostController,
        startDestination = Screen.StatisticScreen.route,
        modifier = Modifier.background(MaterialTheme.colorScheme.background)
    ) {
        composable(route = Screen.StatisticScreen.route) {
            statisticScreen.invoke()
        }
    }
}