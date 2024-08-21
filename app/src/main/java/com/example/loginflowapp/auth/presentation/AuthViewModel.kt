package com.example.loginflowapp.auth.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.loginflowapp.auth.data.remote.response.toAuthResponse
import com.example.loginflowapp.auth.domain.repository.AuthRepository
import com.example.loginflowapp.auth.util.Resource
import com.example.loginflowapp.datastore.domain.UserPreferencesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "TOKEN"

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val userPreferencesRepository: UserPreferencesRepository
) : ViewModel() {

    // Auth state
//    private val _authState = MutableStateFlow<AuthState>(AuthState())
//    val authState: StateFlow<AuthState> = _authState.asStateFlow()

    init {
//        readAccessToken()
    }

    // Events
//    fun onEvent(event: AuthEvent) {
//        when (event) {
//            is AuthEvent.OnSignInSubmit -> {
//                viewModelScope.launch {
//                    val response = authRepository.signIn(event.signInDto)
//                    response.collect {
//                        when (it) {
//                            is Resource.Error -> {
//                                _authState.value = _authState.value.copy(
//                                    data = null,
//                                )
//                            }
//
//                            is Resource.Loading -> {
//                                _authState.value = _authState.value.copy(
//                                    isLoading = it.isLoading
//                                )
//                            }
//
//                            is Resource.Success -> {
//                                _authState.value = _authState.value.copy(
//                                    data = it.data.toAuthResponse()
//                                )
//                                if (!it.data.accessToken.isNullOrEmpty()) {
//                                    _authState.value = _authState.value.copy(
//                                        isAuthenticated = true
//                                    )
//                                    userPreferencesRepository.updateAccessToken(it.data.accessToken)
//                                } else {
//                                    _authState.value = _authState.value.copy(
//                                        isAuthenticated = false
//                                    )
//                                }
//                            }
//                        }
//                    }
//                }
//            }
//
//            is AuthEvent.OnSignUpSubmit -> TODO()
//        }
//    }

//    private fun readAccessToken() {
//        viewModelScope.launch {
//            userPreferencesRepository.userPreferencesFlow.map {
//                it.accessToken
//            }.collectLatest {
//                if (it.isNullOrBlank()) {
//                    _authState.value = _authState.value.copy(
//                        isAuthenticated = false
//                    )
//                } else {
//                    _authState.value = _authState.value.copy(
//                        isAuthenticated = true
//                    )
//                }
//            }
//        }
//    }

}