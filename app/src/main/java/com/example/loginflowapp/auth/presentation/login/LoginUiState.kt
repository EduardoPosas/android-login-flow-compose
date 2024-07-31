package com.example.loginflowapp.auth.presentation.login

import com.example.loginflowapp.auth.domain.UiText

data class LoginUiState(
    val email: String = "",
    val password: String = "",
    val errors: Map<String, UiText?> = emptyMap()
)
