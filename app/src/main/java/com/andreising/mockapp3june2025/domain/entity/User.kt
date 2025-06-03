package com.andreising.mockapp3june2025.domain.entity

data class User(
    val id: Int,
    val sex: String,
    val username: String,
    val isOnline: Boolean,
    val age: Int,
    val avatarUrl: String
)
