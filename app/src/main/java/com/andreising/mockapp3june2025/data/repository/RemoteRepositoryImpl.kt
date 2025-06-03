package com.andreising.mockapp3june2025.data.repository

import com.andreising.mockapp3june2025.data.service.ApiService
import com.andreising.mockapp3june2025.domain.repository.RemoteRepository

class RemoteRepositoryImpl(
    private val apiService: ApiService
) : RemoteRepository {
    override suspend fun getUserList() = apiService.getUserList()

    override suspend fun getUserInteractionHistoryList() =
        apiService.getUserInteractionHistoryList()
}