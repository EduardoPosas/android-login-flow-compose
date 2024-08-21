package com.example.loginflowapp.auth.presentation

data class AuthState(
    val isAuthenticated: Boolean = false,
    val data: AuthResponse? = AuthResponse()
)

data class AuthResponse(
    val error: Boolean = false,
    val message: String = "",
    val accessToken: String? = null
)