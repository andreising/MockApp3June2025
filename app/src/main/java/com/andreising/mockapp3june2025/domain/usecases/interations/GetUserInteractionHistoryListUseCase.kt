package com.andreising.mockapp3june2025.domain.usecases.interations

import com.andreising.mockapp3june2025.domain.repository.RemoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetUserInteractionHistoryListUseCase @Inject constructor(
    private val repository: RemoteRepository
) {
    suspend operator fun invoke() = withContext(Dispatchers.IO) {
        repository.getUserInteractionHistoryList()
    }
}