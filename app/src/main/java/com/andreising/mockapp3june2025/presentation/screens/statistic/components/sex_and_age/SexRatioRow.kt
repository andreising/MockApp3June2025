package com.andreising.mockapp3june2025.presentation.screens.statistic.components.sex_and_age

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.andreising.mockapp3june2025.R

@Composable
fun SexRatioRow(
    maleColor: Color,
    femaleColor: Color,
    malePercent: Float,
    femalePercent: Float
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 40.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        MainStatsItem(maleColor, stringResource(R.string.males), malePercent)
        MainStatsItem(femaleColor, stringResource(R.string.females), femalePercent)
    }
}