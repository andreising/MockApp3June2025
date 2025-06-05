package com.andreising.mockapp3june2025.data.mappers

import android.annotation.SuppressLint
import com.andreising.mockapp3june2025.data.dto.StatisticDTO
import com.andreising.mockapp3june2025.data.dto.UserDTO
import com.andreising.mockapp3june2025.domain.entity.InteractionType
import com.andreising.mockapp3june2025.domain.entity.Sex
import com.andreising.mockapp3june2025.domain.entity.User
import com.andreising.mockapp3june2025.domain.entity.UserInteractionHistory
import java.text.SimpleDateFormat
import java.util.Locale

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

    val parsedDates = dates.mapNotNull { dateFormatter.parse(it.toString()) }

    return UserInteractionHistory(
        userId = userId,
        type = type,
        dateList = parsedDates
    )
}

@SuppressLint("ConstantLocale")
private val dateFormatter = SimpleDateFormat("ddMMyyyy", Locale.getDefault())