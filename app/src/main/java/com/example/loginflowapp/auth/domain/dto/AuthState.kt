package com.example.loginflowapp.auth.domain.dto

import com.example.loginflowapp.auth.presentation.AuthResponse

sealed class AuthState {
    data object Idle : AuthState()
    data object Loading : AuthState()
    data class Error(val error: String) : AuthState()
    data class Authenticated(val data: AuthResponse) : AuthState()
}

