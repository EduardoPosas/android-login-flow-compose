package com.example.loginflowapp.auth.presentation.login

sealed interface LoginUiEvent {
    data class EmailChanged(val email: String) : LoginUiEvent
    data class PasswordChanged(val password: String) : LoginUiEvent
    data object FormSubmit : LoginUiEvent
}