package com.andreising.mockapp3june2025.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FileDTO(
    @SerialName("id") val id: Int,
    @SerialName("url") val url: String,
    @SerialName("type") val type: String
)

