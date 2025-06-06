package com.andreising.mockapp3june2025.presentation.screens.statistic.components.visitors

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import com.andreising.mockapp3june2025.presentation.utils.simple_graph.MonthlyChange

@Composable
fun VisitorsCard(visitorsList: State<List<Int>>) {
    val monthlyChange = MonthlyChange.Companion.from(visitorsList.value)
    val valueList = visitorsList.value

    Card(
        modifier = Modifier.Companion.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
    ) {
        VisitorsChartSummary(valueList, monthlyChange)
    }
}