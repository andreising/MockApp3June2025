package com.andreising.mockapp3june2025.presentation.screens.statistic.components.visitors

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.andreising.mockapp3june2025.R
import com.andreising.mockapp3june2025.presentation.utils.simple_graph.MonthlyChange
import com.andreising.mockapp3june2025.presentation.utils.simple_graph.SimpleChart
import com.andreising.mockapp3june2025.presentation.utils.visitor_by_day_graph.VisitorTrendChart
import com.andreising.mockapp3june2025.presentation.utils.visitor_by_day_graph.VisitorTrendChartModel

@Composable
fun VisitorStatistic(
    visitorsList: State<List<Int>>,
    visitByDays: State<List<VisitorTrendChartModel>>
) {
    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        Text(
            text = stringResource(R.string.visitors),
            style = MaterialTheme.typography.titleMedium
        )
        VisitorsCard(visitorsList)
        VisitorsChartCard(visitByDays)
    }
}

@Composable
fun VisitorsChartCard(visitorsList: State<List<VisitorTrendChartModel>>) {
    Card(
        modifier = Modifier.fillMaxWidth(), colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        )
    ) {
        VisitorTrendChart(
            visitorsList = visitorsList.value,
            mainColor = MaterialTheme.colorScheme.primary,
            outline = MaterialTheme.colorScheme.outline,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
        )
    }
}

@Composable
fun VisitorsCard(visitorsList: State<List<Int>>) {

    val monthlyChange: MonthlyChange = MonthlyChange.from(visitorsList.value)
    val valueList: List<Int> = visitorsList.value
    Card(
        modifier = Modifier.fillMaxWidth(), colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            SimpleChart(
                modifier = Modifier
                    .weight(1f)
                    .padding(8.dp),
                values = if (valueList.size != 1) valueList else listOf(0, valueList.first()),
                color = monthlyChange.color
            )
            Column(modifier = Modifier.weight(2f)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = (valueList.lastOrNull() ?: 0).toString(),
                        style = MaterialTheme.typography.titleMedium
                    )
                    monthlyChange.iconId?.let {
                        Spacer(modifier = Modifier.width(4.dp))
                        Icon(
                            modifier = Modifier.height(16.dp),
                            painter = painterResource(it),
                            contentDescription = null,
                            tint = monthlyChange.color
                        )
                    }
                }
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = stringResource(
                        when (monthlyChange) {
                            is MonthlyChange.Increased -> R.string.visitors_increased
                            is MonthlyChange.Unchanged -> R.string.visitors_unchanged
                            is MonthlyChange.Decreased -> R.string.visitors_decreased
                        }
                    ), style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}