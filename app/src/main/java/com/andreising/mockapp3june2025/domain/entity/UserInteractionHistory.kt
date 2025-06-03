package com.andreising.mockapp3june2025.domain.entity

import java.time.LocalDate

data class UserInteractionHistory(
    val userId: Int,
    val type: InteractionType,
    val dateList: List<LocalDate>
)
