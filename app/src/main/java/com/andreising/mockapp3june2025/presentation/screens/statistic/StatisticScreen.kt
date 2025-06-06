package com.andreising.mockapp3june2025.presentation.screens.statistic

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.andreising.mockapp3june2025.R
import com.andreising.mockapp3june2025.presentation.screens.statistic.components.most_recent_visitors.MostRecentVisitors
import com.andreising.mockapp3june2025.presentation.screens.statistic.components.sex_and_age.SexAndAgeChart
import com.andreising.mockapp3june2025.presentation.screens.statistic.components.visitors.VisitorStatistic
import com.andreising.mockapp3june2025.presentation.utils.UiState

@Composable
fun StatisticScreen() {
    val viewModel: StatisticScreenViewModel = hiltViewModel()

    val uiState = viewModel.uiState.collectAsState()

    val viewedUsers = viewModel.viewedUsersCountFlow.collectAsState()

    val visitorList = viewModel.visitorTrendChartListFlow.collectAsState()

    val mostRecentVisitors = viewModel.mostRecentVisitorsFlow.collectAsState()

    val scrollState = rememberScrollState()

    LaunchedEffect(Unit) {
        viewModel.loadData()
    }

    when (val state = uiState.value) {
        is UiState.Loading -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }

        is UiState.Error -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = state.message, color = MaterialTheme.colorScheme.error)
            }
        }

        is UiState.Success -> {
            Column(
                modifier = Modifier
                    .verticalScroll(scrollState)
                    .padding(horizontal = 16.dp)
                    .systemBarsPadding(),
                verticalArrangement = Arrangement.spacedBy(28.dp)
            ) {
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = stringResource(R.string.statistic),
                    style = MaterialTheme.typography.titleLarge
                )
                VisitorStatistic(viewedUsers, visitorList)
                MostRecentVisitors(mostRecentVisitors)
                SexAndAgeChart(mostRecentVisitors)
            }
        }
    }


}