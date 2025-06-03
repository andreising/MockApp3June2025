package com.andreising.mockapp3june2025.data.service

import com.andreising.mockapp3june2025.data.dto.StatisticResponse
import com.andreising.mockapp3june2025.data.dto.UserResponse
import com.andreising.mockapp3june2025.data.mappers.toDomain
import com.andreising.mockapp3june2025.data.source.KtorSource
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.http.URLProtocol
import io.ktor.http.path
import java.io.IOException
import java.net.UnknownHostException

class ApiService(private val httpClient: HttpClient) {
    suspend fun getUserList() = runCatching {
        httpClient.get {
            url {
                protocol = URLProtocol.HTTP
                host = KtorSource.BASE_URL
                path(KtorSource.USERS)
            }
        }.body<UserResponse>().users.map { it.toDomain() }
    }

    suspend fun getUserInteractionHistoryList() = safeCall {
        httpClient.get {
            url {
                protocol = URLProtocol.HTTP
                host = KtorSource.BASE_URL
                path(KtorSource.STATISTICS)
            }
        }.body<StatisticResponse>().statistics.map { it.toDomain() }
    }

    private suspend fun <T> safeCall(block: suspend () -> T): Result<T> = try {
        Result.success(block())
    } catch (e: UnknownHostException) {
        Result.failure(IOException("No internet connection", e))
    } catch (e: io.ktor.client.plugins.ResponseException) {
        Result.failure(IOException("HTTP ${e.response.status}"))
    } catch (e: Exception) {
        Result.failure(e)
    }
}