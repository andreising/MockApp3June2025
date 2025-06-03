package com.andreising.mockapp3june2025.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class StatisticDTO(
    @SerialName("user_id") val userId: Int,
    @SerialName("type") val type: String,
    @SerialName("dates") val dates: List<Long>
)