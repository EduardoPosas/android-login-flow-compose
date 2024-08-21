package com.example.loginflowapp.auth.data.repository

import com.example.loginflowapp.auth.data.remote.AuthServiceApi
import com.example.loginflowapp.auth.data.remote.response.toAuthResponse
import com.example.loginflowapp.auth.domain.dto.AuthState
import com.example.loginflowapp.auth.domain.dto.SignInDto
import com.example.loginflowapp.auth.domain.dto.SignUpDto
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
//    private val _authState = MutableStateFlow<AuthState>(AuthState.Idle)
//    override val authState: StateFlow<AuthState> = _authState.asStateFlow()

    override suspend fun signIn(body: SignInDto): Flow<AuthState> = flow {
//        _authState.value = AuthState.Loading
        emit(AuthState.Loading)
        try {
            // call api service returns AuthDto
            val response = authServiceApi.signin(body)
            // if access token -> save to proto datastore
//            response.accessToken?.let { userPreferencesRepository.updateAccessToken(it) }
            // update local state and map to model
//            _authState.value = AuthState.Authenticated(data = response.toAuthResponse())
            emit(AuthState.Authenticated(data = response.toAuthResponse()))
        } catch (e: Exception) {
            e.printStackTrace()
//            _authState.value = AuthState.Error(error = "Failed to sign in")
            emit(AuthState.Error(error = "Failed to sign in!"))
        }
    }

    override suspend fun signUp(body: SignUpDto): Flow<AuthState> = flow {
        emit(AuthState.Loading)
        try {
            // request endpoint
            val response = authServiceApi.signup(body)
            emit(
                AuthState.Authenticated(
                    data = response.toAuthResponse()
                )
            )
        } catch (e: Exception) {
            e.printStackTrace()
            emit(AuthState.Error(error = "Failed to sign up!"))
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
}