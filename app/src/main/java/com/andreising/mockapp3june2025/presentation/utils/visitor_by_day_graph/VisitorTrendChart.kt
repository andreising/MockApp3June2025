package com.andreising.mockapp3june2025.presentation.utils.visitor_by_day_graph

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.math.abs
import kotlin.math.roundToInt

@Composable
fun VisitorTrendChart(
    visitorsList: List<VisitorTrendChartModel>,
    modifier: Modifier = Modifier,
    strokeWidth: Dp = 3.dp,
    mainColor: Color,
    outline: Color
) {
    if (visitorsList.size < 2) return

    val values = visitorsList.map { it.value }
    val labels = visitorsList.map { it.label }
    var tooltipIndex by remember { mutableStateOf(0) }
    var points by remember { mutableStateOf(listOf<Offset>()) }

    BoxWithConstraints(modifier = modifier.fillMaxWidth()) {
        val boxWidth = this.maxWidth
        val boxHeight = this.maxHeight
        val density = LocalDensity.current.density

        Canvas(
            modifier = Modifier
                .fillMaxWidth()
                .height(240.dp)
                .pointerInput(Unit) {
                    detectTapGestures(
                        onPress = { offset ->
                            tooltipIndex =
                                getClosestPointIndex(size.width, offset.x, values.size) ?: 0
                        }
                    )
                }
                .pointerInput(Unit) {
                    detectDragGestures { change, _ ->
                        tooltipIndex =
                            getClosestPointIndex(size.width, change.position.x, values.size) ?: 0
                    }
                }
        ) {
            val width = size.width
            val height = size.height
            val offsetRatio = 0.1f
            val leftOffset = width * offsetRatio
            val rightOffset = width * (1f - offsetRatio)
            val topY = height * offsetRatio
            val bottomY = height * (1f - offsetRatio)

            drawDashedLine(topY, width, outline)
            drawDashedLine(bottomY, width, outline)

            val graphWidth = rightOffset - leftOffset
            val gap = graphWidth / (values.size - 1)
            val maxVal = values.maxOrNull()?.toFloat() ?: return@Canvas
            val graphTopY = height * 0.4f
            val graphBottomY = height * 0.9f
            val graphHeight = graphBottomY - graphTopY

            points = values.mapIndexed { i, value ->
                val x = leftOffset + i * gap
                val scaled = (value / maxVal).coerceIn(0f, 1f)
                val y = graphBottomY - scaled * graphHeight
                Offset(x, y)
            }

            tooltipIndex.let { index ->
                points.getOrNull(index)?.let { pt ->
                    drawVerticalDashedLine(pt.x, topY, bottomY, mainColor)
                    drawHorizontalDashedLine(pt.y, width, outline)
                }
            }

            drawPath(
                path = Path().apply {
                    moveTo(points.first().x, points.first().y)
                    points.drop(1).forEach { lineTo(it.x, it.y) }
                },
                color = mainColor,
                style = Stroke(width = strokeWidth.toPx(), cap = StrokeCap.Round)
            )

            points.forEach {
                drawCircle(Color.White, 5.dp.toPx(), center = it)
                drawCircle(
                    mainColor,
                    5.dp.toPx(),
                    center = it,
                    style = Stroke(width = strokeWidth.toPx())
                )
            }

            labels.forEachIndexed { i, label ->
                val paint = android.graphics.Paint().apply {
                    color = outline.toArgb()
                    textAlign = android.graphics.Paint.Align.CENTER
                    textSize = 10.sp.toPx()
                    isAntiAlias = true
                }
                drawContext.canvas.nativeCanvas.drawText(
                    label,
                    points[i].x,
                    size.height,
                    paint
                )
            }
        }

        tooltipIndex.let { index ->
            ChartInfoCard(
                labelTitle = visitorsList[index].labelTitle,
                valueTitle = visitorsList[index].valueTitle,
                point = points.getOrNull(index) ?: return@let,
                canvasOffset = IntOffset(
                    (boxWidth.value * density).roundToInt(),
                    (boxHeight.value * density).roundToInt()
                ),
                mainColor = mainColor,
                secondaryColor = outline,
                modifier = Modifier.align(Alignment.TopStart)
            )
        }
    }
}

private fun getClosestPointIndex(width: Int, xOperand: Float, count: Int): Int? {
    val offsetRatio = 0.1f
    val leftOffset = width * offsetRatio
    val rightOffset = width * (1f - offsetRatio)
    val gap = (rightOffset - leftOffset) / (count - 1)
    return (0 until count).minByOrNull { i -> abs(leftOffset + i * gap - xOperand) }
}

private fun DrawScope.drawDashedLine(y: Float, width: Float, color: Color) {
    drawHorizontalDashedLine(y, width, color)
}

private fun DrawScope.drawHorizontalDashedLine(y: Float, width: Float, color: Color) {
    val dash = 10.dp.toPx()
    val space = 6.dp.toPx()
    var x = 0f
    while (x < width) {
        drawLine(
            color,
            Offset(x, y),
            Offset((x + dash).coerceAtMost(width), y),
            strokeWidth = 2.dp.toPx()
        )
        x += dash + space
    }
}

private fun DrawScope.drawVerticalDashedLine(x: Float, top: Float, bottom: Float, color: Color) {
    val dash = 10.dp.toPx()
    val space = 6.dp.toPx()
    var y = top
    while (y < bottom) {
        drawLine(
            color,
            Offset(x, y),
            Offset(x, (y + dash).coerceAtMost(bottom)),
            strokeWidth = 2.dp.toPx()
        )
        y += dash + space
    }
}

@Composable
private fun ChartInfoCard(
    labelTitle: String,
    valueTitle: String,
    point: Offset,
    canvasOffset: IntOffset,
    mainColor: Color,
    secondaryColor: Color,
    cardHeightPadding: Dp = 16.dp,
    modifier: Modifier = Modifier
) {
    var cardSize by remember { mutableStateOf(IntSize.Zero) }
    Card(
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
        modifier = modifier
            .onGloballyPositioned { cardSize = it.size }
            .offset {
                val x = (point.x - cardSize.width / 2).roundToInt()
                val y = (point.y - cardHeightPadding.toPx() - cardSize.height).roundToInt()
                IntOffset(
                    x.coerceIn(0, canvasOffset.x - cardSize.width),
                    y.coerceIn(0, canvasOffset.y - cardSize.height)
                )
            }
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(valueTitle, fontSize = 16.sp, color = mainColor)
            Text(labelTitle, fontSize = 16.sp, color = secondaryColor)
        }
    }
}