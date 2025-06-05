package com.andreising.mockapp3june2025.presentation.screens.statistic.components.visitors

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.andreising.mockapp3june2025.presentation.utils.visitor_by_day_graph.VisitorTrendChart
import com.andreising.mockapp3june2025.presentation.utils.visitor_by_day_graph.VisitorTrendChartModel

@Composable
fun VisitorsChartCard(visitorsList: State<List<VisitorTrendChartModel>>) {
    Card(
        modifier = Modifier.Companion.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        )
    ) {
        VisitorTrendChart(
            visitorsList = visitorsList.value,
            mainColor = MaterialTheme.colorScheme.primary,
            outline = MaterialTheme.colorScheme.outline,
            modifier = Modifier.Companion.padding(horizontal = 16.dp, vertical = 8.dp)
        )
    }
}