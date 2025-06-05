package com.andreising.mockapp3june2025.presentation.utils.simple_graph

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun SimpleChart(
    values: List<Int>,
    color: Color,
    modifier: Modifier = Modifier,
    strokeWidth: Dp = 3.dp
) {
    if (values.isEmpty()) return

    Canvas(modifier = modifier.fillMaxWidth().aspectRatio(2f)) {
        val width = size.width.times(0.8f)
        val height = size.height.times(0.8f)

        val maxVal = values.maxOrNull()?.toFloat() ?: return@Canvas
        val minVal = values.minOrNull()?.toFloat() ?: 0f
        val range = (maxVal - minVal).takeIf { it != 0f } ?: 1f

        val pointGap = width / (values.size - 1).coerceAtLeast(1)

        val points = values.mapIndexed { index, value ->
            val x = index * pointGap
            val y = height - ((value - minVal) / range) * height
            Offset(x, y)
        }

        if (points.size < 2) return@Canvas

        fun Path.cubicSmoothCurve(points: List<Offset>) {
            if (points.size < 2) return
            moveTo(points.first().x, points.first().y)
            for (i in 1 until points.size) {
                val prev = points[i - 1]
                val curr = points[i]
                val midPointX = (prev.x + curr.x) / 2
                cubicTo(
                    midPointX, prev.y,
                    midPointX, curr.y,
                    curr.x, curr.y
                )
            }
        }

        // main path with cubic bezier
        val path = Path().apply {
            cubicSmoothCurve(points)
        }

        drawPath(
            path = path,
            color = color,
            style = Stroke(width = strokeWidth.toPx(), cap = StrokeCap.Round)
        )

        // circle (last point)
        val last = points.last()
        drawCircle(
            color = Color.White,
            radius = (strokeWidth.times(1.5f)).toPx(),
            center = last
        )
        drawCircle(
            color = color,
            radius = (strokeWidth.times(1.5f)).toPx(),
            center = last,
            style = Stroke(width = strokeWidth.toPx(), cap = StrokeCap.Round)
        )
    }
}
