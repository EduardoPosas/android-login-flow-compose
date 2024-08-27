package com.example.loginflowapp.auth.domain.repository

import com.example.loginflowapp.auth.domain.model.AuthState
import com.example.loginflowapp.auth.domain.model.SignIn
import com.example.loginflowapp.auth.domain.model.SignUp
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    //    val authState: StateFlow<AuthState>
    suspend fun signIn(body: SignIn): Flow<AuthState>
    suspend fun signUp(body: SignUp): Flow<AuthState>
    suspend fun logOut()
    suspend fun getAccessToken(): String?
    suspend fun updateAccessToken(token: String)
}