package com.andreising.mockapp3june2025.presentation.screens.statistic.components.visitors

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.andreising.mockapp3june2025.R
import com.andreising.mockapp3june2025.presentation.utils.simple_graph.MonthlyChange

@Composable
fun VisitorsChangeLabel(change: MonthlyChange) {
    val changeText = when (change) {
        is MonthlyChange.Increased -> R.string.visitors_increased
        is MonthlyChange.Unchanged -> R.string.visitors_unchanged
        is MonthlyChange.Decreased -> R.string.visitors_decreased
    }

    Text(
        text = stringResource(changeText),
        style = MaterialTheme.typography.bodyMedium
    )
}