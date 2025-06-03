package com.andreising.mockapp3june2025.domain.usecases.users

import com.andreising.mockapp3june2025.domain.repository.RemoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetUsersListUseCase(
    private val repository: RemoteRepository
) {
    suspend operator fun invoke() = withContext(Dispatchers.IO) {
        repository.getUserList()
    }
}