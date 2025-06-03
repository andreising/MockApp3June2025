package com.andreising.mockapp3june2025.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class StatisticResponse(
    @SerialName("statistics") val statistics: List<StatisticDTO>
)