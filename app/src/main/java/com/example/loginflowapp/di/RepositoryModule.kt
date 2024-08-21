package com.example.loginflowapp.di

import com.example.loginflowapp.auth.data.repository.AuthRepositoryImpl
import com.example.loginflowapp.auth.domain.repository.AuthRepository
import com.example.loginflowapp.datastore.data.UserPreferencesRepositoryImpl
import com.example.loginflowapp.datastore.domain.UserPreferencesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindAuthRepository(authRepositoryImpl: AuthRepositoryImpl): AuthRepository

    @Binds
    @Singleton
    abstract fun bindUserPreferencesRepository(userPreferencesRepositoryImpl: UserPreferencesRepositoryImpl): UserPreferencesRepository
}