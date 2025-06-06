package com.andreising.mockapp3june2025.presentation.screens.statistic.components.visitors

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun VisitorsValueText(color: Color, value: Int, iconId: Int?) {
    Row(verticalAlignment = Alignment.Companion.CenterVertically) {
        Text(
            text = value.toString(),
            style = MaterialTheme.typography.titleMedium
        )
        iconId?.let {
            Spacer(modifier = Modifier.Companion.width(4.dp))
            Icon(
                modifier = Modifier.Companion.height(16.dp),
                painter = painterResource(it),
                contentDescription = null,
                tint = color
            )
        }
    }
}