package com.example.loginflowapp.auth.data.remote.response

import com.example.loginflowapp.auth.domain.model.AuthResponse


data class Auth(
    val error: Boolean,
    val message: String,
    val accessToken: String? = null
)

fun Auth.toAuthResponse() = AuthResponse(
    error = error,
    message = message,
    accessToken = accessToken
)
