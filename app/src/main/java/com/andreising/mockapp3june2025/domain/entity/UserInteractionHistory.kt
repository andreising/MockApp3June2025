package com.andreising.mockapp3june2025.domain.entity

import java.util.Date

data class UserInteractionHistory(
    val userId: Int,
    val type: InteractionType,
    val dateList: List<Date>
)
