package com.andreising.mockapp3june2025.domain.usecases.interations

import com.andreising.mockapp3june2025.domain.repository.RemoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetUserInteractionHistoryListUseCase(
    private val repository: RemoteRepository
) {
    suspend operator fun invoke() = withContext(Dispatchers.IO) {
        repository.getUserInteractionHistoryList()
    }
}