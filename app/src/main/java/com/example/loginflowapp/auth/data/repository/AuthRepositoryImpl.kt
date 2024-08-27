package com.example.loginflowapp.auth.data.repository

import com.example.loginflowapp.auth.data.remote.AuthServiceApi
import com.example.loginflowapp.auth.data.remote.response.toAuthResponse
import com.example.loginflowapp.auth.domain.model.AuthState
import com.example.loginflowapp.auth.domain.model.SignIn
import com.example.loginflowapp.auth.domain.model.SignUp
import com.example.loginflowapp.auth.domain.repository.AuthRepository
import com.example.loginflowapp.datastore.domain.UserPreferencesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authServiceApi: AuthServiceApi,
    private val userPreferencesRepository: UserPreferencesRepository
) : AuthRepository {

    override suspend fun signIn(body: SignIn): Flow<AuthState> = flow {
        emit(AuthState.Loading(isLoading = true))
        try {
            // call api service returns Auth response
            val response = authServiceApi.signin(body)
            emit(AuthState.Success(data = response.toAuthResponse()))
        } catch (e: Exception) {
            e.printStackTrace()
            emit(AuthState.Error(data = null, error = "Failed to sign in"))
        } finally {
            emit(AuthState.Loading(isLoading = false))
        }
    }

    override suspend fun signUp(body: SignUp): Flow<AuthState> = flow {
        emit(AuthState.Loading(isLoading = true))
        try {
            // request sign up endpoint
            val response = authServiceApi.signup(body)
            emit(
                AuthState.Success(
                    data = response.toAuthResponse()
                )
            )
        } catch (e: Exception) {
            e.printStackTrace()
            emit(AuthState.Error(data = null, error = "Failed to sign up!"))
        } finally {
            AuthState.Loading(isLoading = false)
        }
    }

    override suspend fun logOut() {
//        _authState.value = AuthState.Idle
        userPreferencesRepository.updateAccessToken("")
    }

    override suspend fun getAccessToken(): String? {
        var token: String? = null
        userPreferencesRepository.userPreferencesFlow.map {
            it.accessToken
        }.collectLatest {
            token = it
        }
        return token
    }

    override suspend fun updateAccessToken(token: String) {
        userPreferencesRepository.updateAccessToken(token)
    }
}