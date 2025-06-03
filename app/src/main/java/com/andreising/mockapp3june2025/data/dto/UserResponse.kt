package com.andreising.mockapp3june2025.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserResponse(
    @SerialName("users") val users: List<UserDTO>
)