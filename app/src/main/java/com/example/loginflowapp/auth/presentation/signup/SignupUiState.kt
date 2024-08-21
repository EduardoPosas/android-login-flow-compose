package com.example.loginflowapp.auth.presentation.signup

import com.example.loginflowapp.auth.domain.UiText
import com.example.loginflowapp.auth.domain.dto.SignUpDto

data class SignupUiState(
    val firstName: String = "",
    val lastName: String = "",
    val email: String = "",
    val password: String = "",
    val termsOfUse: Boolean = false,
    val errors: Map<String, UiText?> = emptyMap(),
    val response: Response = Response()
)

data class Response(
    val error: Boolean = false,
    val message: String = "",
    val loading: Boolean = false
)

fun SignupUiState.toSignUpDto() = SignUpDto(
    firstName = firstName,
    lastName = lastName,
    email = email,
    password = password
)