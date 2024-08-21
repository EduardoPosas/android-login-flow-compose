package com.example.loginflowapp.auth.domain.dto

data class SignUpDto(
    val firstName: String,
    val lastName: String,
    val email: String,
    val password: String,
)
