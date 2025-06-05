package com.andreising.mockapp3june2025.presentation.screens.statistic.components.visitors

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.andreising.mockapp3june2025.R
import com.andreising.mockapp3june2025.presentation.screens.statistic.components.sex_and_age.PeriodSelector
import com.andreising.mockapp3june2025.presentation.utils.simple_graph.MonthlyChange
import com.andreising.mockapp3june2025.presentation.utils.visitor_by_day_graph.VisitorTrendChartModel

@Composable
fun VisitorStatistic(
    visitorsList: State<List<Int>>,
    visitByDays: State<List<VisitorTrendChartModel>>
) {
    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        VisitorsTitle()
        VisitorsCard(visitorsList)
        VisitorsPeriodSelector()
        VisitorsChartCard(visitByDays)
    }
}

@Composable
private fun VisitorsTitle() {
    Text(
        text = stringResource(R.string.visitors),
        style = MaterialTheme.typography.titleMedium
    )
}

@Composable
private fun VisitorsPeriodSelector() {
    PeriodSelector(
        periods = listOf(R.string.by_day, R.string.by_week, R.string.by_months),
        selected = R.string.by_day,
        onSelect = {}
    )
}

@Composable
fun VisitorsInfoPanel(change: MonthlyChange, currentValue: Int, modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        VisitorsValueText(change.color, currentValue, change.iconId)
        Spacer(modifier = Modifier.height(8.dp))
        VisitorsChangeLabel(change)
    }
}

