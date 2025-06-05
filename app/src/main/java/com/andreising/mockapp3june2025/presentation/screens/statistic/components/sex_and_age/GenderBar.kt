package com.andreising.mockapp3june2025.presentation.screens.statistic.components.sex_and_age

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlin.math.roundToInt

@Composable
fun GenderBar(count: Int, total: Int, color: Color) {
    val percent = (count * 1f / total).coerceAtLeast(0.02f)
    Row(
        modifier = Modifier.Companion.fillMaxWidth(),
        verticalAlignment = Alignment.Companion.CenterVertically
    ) {
        Box(
            modifier = Modifier.Companion
                .fillMaxWidth(percent)
                .height(5.dp)
                .background(color, shape = MaterialTheme.shapes.small)
        )
        Spacer(modifier = Modifier.Companion.width(8.dp))
        Text(
            text = "${(count * 100f / total).roundToInt()}%",
            style = MaterialTheme.typography.labelSmall
        )
    }
}