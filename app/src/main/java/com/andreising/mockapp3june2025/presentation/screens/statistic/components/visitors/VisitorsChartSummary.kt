package com.andreising.mockapp3june2025.presentation.screens.statistic.components.visitors

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.andreising.mockapp3june2025.presentation.utils.simple_graph.MonthlyChange

@Composable
fun VisitorsChartSummary(values: List<Int>, monthlyChange: MonthlyChange) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        VisitorsChartLine(
            values = if (values.size != 1) values else listOf(0, values.first()),
            color = monthlyChange.color,
            modifier = Modifier
                .weight(1f)
                .padding(8.dp)
        )
        VisitorsInfoPanel(monthlyChange, values.lastOrNull() ?: 0, Modifier.weight(2f))
    }
}