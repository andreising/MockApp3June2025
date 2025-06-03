package com.andreising.mockapp3june2025.di

import com.andreising.mockapp3june2025.data.repository.RemoteRepositoryImpl
import com.andreising.mockapp3june2025.domain.repository.RemoteRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class MainModule {
    @Binds
    abstract fun provideRemoteRepository(impl: RemoteRepositoryImpl): RemoteRepository
}