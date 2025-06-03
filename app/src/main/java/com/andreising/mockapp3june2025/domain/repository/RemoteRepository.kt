package com.andreising.mockapp3june2025.domain.repository

import com.andreising.mockapp3june2025.domain.entity.User
import com.andreising.mockapp3june2025.domain.entity.UserInteractionHistory

interface RemoteRepository {
    suspend fun getUserList(): List<User>

    suspend fun getUserInteractionHistoryList(): List<UserInteractionHistory>
}