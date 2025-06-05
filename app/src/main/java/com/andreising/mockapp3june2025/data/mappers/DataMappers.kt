package com.andreising.mockapp3june2025.data.mappers

import android.annotation.SuppressLint
import com.andreising.mockapp3june2025.data.dto.StatisticDTO
import com.andreising.mockapp3june2025.data.dto.UserDTO
import com.andreising.mockapp3june2025.domain.entity.InteractionType
import com.andreising.mockapp3june2025.domain.entity.Sex
import com.andreising.mockapp3june2025.domain.entity.User
import com.andreising.mockapp3june2025.domain.entity.UserInteractionHistory
import java.text.SimpleDateFormat
import java.util.Date

fun UserDTO.toDomain(): User {
    val avatar = files.firstOrNull { it.type == "avatar" }?.url ?: error("Avatar url is nullable")
    return User(
        id = id,
        sex = Sex.get(sex),
        username = username,
        isOnline = isOnline,
        age = age,
        avatarUrl = avatar
    )
}

fun StatisticDTO.toDomain(): UserInteractionHistory {
    val type = InteractionType.valueOf(type.uppercase())
    val parsedDates = dates.mapNotNull { parseFlexibleDate(it.toString()) }

    return UserInteractionHistory(
        userId = userId,
        type = type,
        dateList = parsedDates
    )
}

@SuppressLint("SimpleDateFormat")
private fun parseFlexibleDate(dateStr: String): Date? {
    return when (dateStr.length) {
        8 -> SimpleDateFormat("ddMMyyyy").parse(dateStr)

        7 -> {
            val normalized = "0$dateStr"
            SimpleDateFormat("ddMMyyyy").parse(normalized)
        }

        else -> null
    }
}