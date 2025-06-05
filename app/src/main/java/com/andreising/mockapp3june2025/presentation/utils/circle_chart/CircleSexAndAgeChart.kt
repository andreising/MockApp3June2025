package com.andreising.mockapp3june2025.presentation.utils.circle_chart

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun CircleSexAndAgeChart(
    malePercent: Float,
    femalePercent: Float,
    modifier: Modifier = Modifier,
    maleColor: Color = Color.Blue,
    femaleColor: Color = Color.Magenta,
    strokeWidth: Dp = 16.dp
) {

    Canvas(modifier = modifier.fillMaxWidth().height(240.dp)) {
        val strokePx = strokeWidth.toPx()
        val diameter = size.minDimension - strokePx

        val startAngle = -90f
        val gapDegrees = 10f
        val maleSweep = 360f * malePercent - gapDegrees
        val femaleSweep = 360f * femalePercent - gapDegrees

        drawArc(
            color = femaleColor,
            startAngle = startAngle+gapDegrees/2,
            sweepAngle = femaleSweep,
            useCenter = false,
            topLeft = Offset((size.width - diameter) / 2f, (size.height - diameter) / 2f),
            size = Size(diameter, diameter),
            style = Stroke(width = strokePx, cap = StrokeCap.Round)
        )

        drawArc(
            color = maleColor,
            startAngle = startAngle + femaleSweep + gapDegrees.times(1.5f) ,
            sweepAngle = maleSweep,
            useCenter = false,
            topLeft = Offset((size.width - diameter) / 2f, (size.height - diameter) / 2f),
            size = Size(diameter, diameter),
            style = Stroke(width = strokePx, cap = StrokeCap.Round)
        )
    }
}
