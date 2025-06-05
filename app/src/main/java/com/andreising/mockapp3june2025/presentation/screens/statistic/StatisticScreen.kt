package com.andreising.mockapp3june2025.presentation.screens.statistic

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.andreising.mockapp3june2025.R
import com.andreising.mockapp3june2025.presentation.screens.statistic.components.most_recent_visitors.MostRecentVisitors
import com.andreising.mockapp3june2025.presentation.screens.statistic.components.sex_and_age.SexAndAgeChart
import com.andreising.mockapp3june2025.presentation.screens.statistic.components.visitors.VisitorStatistic
import kotlin.random.Random

@Composable
fun StatisticScreen() {
    val viewModel: StatisticScreenViewModel = hiltViewModel()

    val userList = viewModel.userListFlow.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.loadData()
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(28.dp)
    ) {
        item {
            Text(
                text = stringResource(R.string.statistic),
                style = MaterialTheme.typography.titleLarge
            )
        }
        item { VisitorStatistic(List(6) { Random.nextInt(10000) }) }
        item { MostRecentVisitors(userList) }
        item { SexAndAgeChart(emptyList()) }
    }
}