package com.example.loginflowapp.auth.data.remote.response

import com.example.loginflowapp.auth.presentation.AuthResponse

data class SignUp(
    val error: Boolean,
    val message: String,
)

fun SignUp.toAuthResponse() = AuthResponse(
    error = error,
    message = message
)
