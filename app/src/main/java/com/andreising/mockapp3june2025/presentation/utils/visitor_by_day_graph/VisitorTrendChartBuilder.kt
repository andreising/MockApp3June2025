package com.andreising.mockapp3june2025.presentation.utils.visitor_by_day_graph

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object VisitorTrendChartBuilder {
    private val labelFormatter = SimpleDateFormat("dd.MM", Locale("ru"))

    private fun formatDateToRussian(date: Date): String {
        val format = SimpleDateFormat("d MMMM", Locale("ru"))
        return format.format(date)
    }

    private fun formatUsersCount(count: Int): String {
        return when {
            count % 100 in 11..14 -> "$count пользователей"
            count % 10 == 1 -> "$count пользователь"
            count % 10 in 2..4 -> "$count пользователя"
            else -> "$count пользователей"
        }
    }

    fun getModelByDateAndInt(pair: Pair<Date, Int>): VisitorTrendChartModel {
        return VisitorTrendChartModel(
            labelFormatter.format(pair.first),
            pair.second,
            formatDateToRussian(pair.first),
            formatUsersCount(pair.second)
        )
    }
}