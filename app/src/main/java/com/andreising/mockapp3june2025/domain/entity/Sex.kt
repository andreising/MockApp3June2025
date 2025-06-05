package com.andreising.mockapp3june2025.domain.entity

enum class Sex {
    MALE, FEMALE;

    companion object {
        fun get(string: String): Sex {
            return when(string) {
                "M" -> MALE
                "W" -> FEMALE
                else -> error("Illegal code")
            }
        }
    }
}