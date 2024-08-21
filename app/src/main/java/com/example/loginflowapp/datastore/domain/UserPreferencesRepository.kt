package com.example.loginflowapp.datastore.domain

import com.example.loginflowapp.datastore.UserPreferences
import kotlinx.coroutines.flow.Flow

interface UserPreferencesRepository {
    val userPreferencesFlow: Flow<UserPreferences>
    suspend fun updateAccessToken(accessToken: String): Unit
}