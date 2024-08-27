package com.example.loginflowapp.auth.presentation.login

import com.example.loginflowapp.auth.domain.model.SignIn

sealed interface LoginUiEvent {
    data class EmailChanged(val email: String) : LoginUiEvent
    data class PasswordChanged(val password: String) : LoginUiEvent
    data class FormSubmit(val signIn: SignIn) : LoginUiEvent
}