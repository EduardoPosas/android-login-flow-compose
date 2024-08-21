package com.example.loginflowapp.auth.presentation

import com.example.loginflowapp.auth.domain.dto.SignInDto

sealed interface AuthEvent {
    data class OnSignInSubmit(
        val signInDto: SignInDto
    ) : AuthEvent

    data class OnSignUpSubmit(
        val email: String,
        val password: String,
        val firstName: String,
        val lastName: String
    ) : AuthEvent
}