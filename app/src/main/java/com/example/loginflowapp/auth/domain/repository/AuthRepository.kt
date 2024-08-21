package com.example.loginflowapp.auth.domain.repository

import com.example.loginflowapp.auth.domain.dto.AuthState
import com.example.loginflowapp.auth.domain.dto.SignInDto
import com.example.loginflowapp.auth.domain.dto.SignUpDto
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    //    val authState: StateFlow<AuthState>
    suspend fun signIn(body: SignInDto): Flow<AuthState>
    suspend fun signUp(body: SignUpDto): Flow<AuthState>
    suspend fun logOut()
    suspend fun getAccessToken(): String?
}