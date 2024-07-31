package com.example.loginflowapp.auth.presentation.signup

import com.example.loginflowapp.auth.domain.UiText

data class SignupUiState(
    val firstName: String = "",
    val lastName: String = "",
    val email: String = "",
    val password: String = "",
    val termsOfUse: Boolean = false,
    val errors: Map<String, UiText?> = emptyMap()
)
