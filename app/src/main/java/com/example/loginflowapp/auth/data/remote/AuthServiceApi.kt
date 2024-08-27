package com.example.loginflowapp.auth.data.remote

import com.example.loginflowapp.auth.data.remote.response.Auth
import com.example.loginflowapp.auth.domain.model.SignIn
import com.example.loginflowapp.auth.domain.model.SignUp
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthServiceApi {
    @POST("signin")
    suspend fun signin(@Body body: SignIn): Auth

    @POST("signup")
    suspend fun signup(@Body body: SignUp): Auth

    companion object {
        const val BASE_URL = "http://192.168.1.69:3000/auth/"
    }
}