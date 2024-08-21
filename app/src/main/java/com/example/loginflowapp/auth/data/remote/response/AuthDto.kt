package com.example.loginflowapp.auth.data.remote.response

import com.example.loginflowapp.auth.presentation.AuthResponse

data class AuthDto(
    val error: Boolean,
    val message: String,
    val accessToken: String? = null
)

fun AuthDto.toAuthResponse() = AuthResponse(
    error = error,
    message = message,
    accessToken = accessToken
)
