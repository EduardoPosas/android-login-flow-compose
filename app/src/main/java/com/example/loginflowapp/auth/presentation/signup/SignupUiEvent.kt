package com.example.loginflowapp.auth.presentation.signup

sealed interface SignupUiEvent {
    data class FirstNameChanged(val firstName: String) : SignupUiEvent
    data class LastNameChanged(val lastName: String) : SignupUiEvent
    data class EmailChanged(val email: String) : SignupUiEvent
    data class PasswordChanged(val password: String) : SignupUiEvent
    data object SignupSubmit : SignupUiEvent
}