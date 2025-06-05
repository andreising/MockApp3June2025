package com.andreising.mockapp3june2025.presentation.utils.simple_graph

import androidx.compose.ui.graphics.Color
import com.andreising.mockapp3june2025.R
import com.andreising.mockapp3june2025.presentation.theme.Green
import com.andreising.mockapp3june2025.presentation.theme.Red

sealed class MonthlyChange(
    open val difference: Int,
    val color: Color,
    val iconId: Int?
) {
    data class Increased(override val difference: Int) : MonthlyChange(difference, Green, R.drawable.increased)
    data class Decreased(override val difference: Int) : MonthlyChange(difference, Red, R.drawable.decreased)
    object Unchanged : MonthlyChange(0, Color.Gray, null)

    companion object {
        fun from(values: List<Int>): MonthlyChange {
            if (values.isEmpty()) return Unchanged
            if (values.size < 2) return Increased(values.last())
            val diff = values.last() - values[values.lastIndex - 1]
            return when {
                diff > 0 -> Increased(diff)
                diff < 0 -> Decreased(-diff)
                else -> Unchanged
            }
        }
    }
}