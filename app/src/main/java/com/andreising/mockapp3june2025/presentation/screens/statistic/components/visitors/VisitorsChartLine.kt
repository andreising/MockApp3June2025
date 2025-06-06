package com.andreising.mockapp3june2025.presentation.screens.statistic.components.visitors

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.andreising.mockapp3june2025.presentation.utils.simple_graph.SimpleChart

@Composable
fun VisitorsChartLine(
    values: List<Int>,
    color: Color,
    modifier: Modifier = Modifier.Companion
) {
    SimpleChart(
        modifier = modifier,
        values = values,
        color = color
    )
}