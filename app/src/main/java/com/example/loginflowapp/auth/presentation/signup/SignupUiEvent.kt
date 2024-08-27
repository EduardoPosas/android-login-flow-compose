package com.example.loginflowapp.auth.presentation.signup

import com.example.loginflowapp.auth.domain.model.SignUp

sealed interface SignupUiEvent {
    data class FirstNameChanged(val firstName: String) : SignupUiEvent
    data class LastNameChanged(val lastName: String) : SignupUiEvent
    data class EmailChanged(val email: String) : SignupUiEvent
    data class PasswordChanged(val password: String) : SignupUiEvent
    data class TermsOfUseChanged(val terms: Boolean) : SignupUiEvent
    data class SignupSubmit(val signUpDto: SignUp) : SignupUiEvent
}