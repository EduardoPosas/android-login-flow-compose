package com.example.loginflowapp.auth.domain.model


sealed interface AuthState {
    data class Loading(val isLoading: Boolean) : AuthState
    data class Error(val error: String, val data: AuthResponse?) : AuthState
    data class Success(val data: AuthResponse) : AuthState
}

data class AuthResponse(
    val error: Boolean = false,
    val message: String = "",
    val accessToken: String? = null
)