package com.andreising.mockapp3june2025.presentation.screens.statistic.components.visitors

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.andreising.mockapp3june2025.presentation.utils.simple_graph.MonthlyChange

@Composable
fun VisitorsInfoPanel(change: MonthlyChange, currentValue: Int, modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        VisitorsValueText(change.color, currentValue, change.iconId)
        Spacer(modifier = Modifier.height(8.dp))
        VisitorsChangeLabel(change)
    }
}