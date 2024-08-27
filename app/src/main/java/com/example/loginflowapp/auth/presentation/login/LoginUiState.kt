package com.example.loginflowapp.auth.presentation.login

import com.example.loginflowapp.auth.domain.UiText
import com.example.loginflowapp.auth.domain.model.SignIn

data class LoginUiState(
    val email: String = "",
    val password: String = "",
    val errors: Map<String, UiText?> = emptyMap(),
    val authenticated: Authenticated = Authenticated()
)

data class Authenticated(
    val isAuthenticated: Boolean = false,
    val error: Boolean = false,
    val message: String = "",
    val loading: Boolean = false,
)

fun LoginUiState.toSignIn() = SignIn(
    email = email,
    password = password
)