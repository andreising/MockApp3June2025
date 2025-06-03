package com.andreising.mockapp3june2025.di

import com.andreising.mockapp3june2025.data.source.KtorSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient

@Module
@InstallIn(SingletonComponent::class)
object DataModule {
    @Provides
    fun provideHttpClient(): HttpClient = KtorSource.getHttpClient()
}