package com.andreising.mockapp3june2025.data.repository

import com.andreising.mockapp3june2025.data.service.ApiService
import com.andreising.mockapp3june2025.data.source.KtorSource
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertTrue
import org.junit.Test
import java.util.Date

class RemoteRepositoryImplTest {

    private val apiService = ApiService(KtorSource.getHttpClient())
    private val repository = RemoteRepositoryImpl(apiService)

    @Test
    fun `getUserList returns non-empty valid user list`() = runBlocking {
        val result = repository.getUserList()

        assertTrue(result.isSuccess)
        val user = result.getOrThrow().first()
        assertTrue(user.id > 0)
        assertTrue(user.username.isNotBlank())
        assertTrue(user.avatarUrl.startsWith("http"))
    }

    @Test
    fun `getUserInteractionHistoryList returns valid history`() = runBlocking {
        val result = repository.getUserInteractionHistoryList()
        println("result is $result")

        assertTrue(result.isSuccess)
        val history = result.getOrThrow().first()
        assertTrue(history.userId > 0)
        assertTrue(history.dateList.all { it is Date })
        assertTrue(history.dateList.isNotEmpty())
    }
}