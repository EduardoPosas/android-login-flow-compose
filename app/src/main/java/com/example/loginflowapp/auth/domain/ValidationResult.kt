package com.example.loginflowapp.auth.domain

data class ValidationResult(
    val success: Boolean,
    val errorMessage: UiText? = null
)
