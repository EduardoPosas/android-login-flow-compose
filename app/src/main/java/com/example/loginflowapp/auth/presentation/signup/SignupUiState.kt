package com.example.loginflowapp.auth.presentation.signup

data class SignupUiState(
    val firstName: String = "",
    val lastName: String = "",
    val email: String = "",
    val password: String = "",
    val termsOfUse: Boolean = false
)
