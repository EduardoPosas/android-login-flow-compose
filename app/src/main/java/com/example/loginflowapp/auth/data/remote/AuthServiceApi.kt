package com.example.loginflowapp.auth.data.remote

import com.example.loginflowapp.auth.data.remote.response.AuthDto
import com.example.loginflowapp.auth.data.remote.response.SignUp
import com.example.loginflowapp.auth.domain.dto.SignInDto
import com.example.loginflowapp.auth.domain.dto.SignUpDto
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthServiceApi {
    @POST("signin")
    suspend fun signin(@Body body: SignInDto): AuthDto

    @POST("signup")
    suspend fun signup(@Body body: SignUpDto): SignUp

    companion object {
        const val BASE_URL = "http://192.168.1.69:3000/auth/"
    }
}