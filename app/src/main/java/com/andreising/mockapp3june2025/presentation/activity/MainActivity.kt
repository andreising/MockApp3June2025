package com.andreising.mockapp3june2025.presentation.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.andreising.mockapp3june2025.presentation.navigation.AppNavigation
import com.andreising.mockapp3june2025.presentation.screens.statistic.StatisticScreen
import com.andreising.mockapp3june2025.presentation.theme.MockApp3June2025Theme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MockApp3June2025Theme {
                AppNavigation(
                    statisticScreen = { StatisticScreen() }
                )
            }
        }
    }
}