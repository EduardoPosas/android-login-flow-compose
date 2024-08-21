package com.example.loginflowapp.auth.presentation.login

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.loginflowapp.auth.domain.UiText
import com.example.loginflowapp.auth.domain.ValidateEmailUseCase
import com.example.loginflowapp.auth.domain.ValidatePasswordUseCase
import com.example.loginflowapp.auth.domain.dto.AuthState
import com.example.loginflowapp.auth.domain.repository.AuthRepository
import com.example.loginflowapp.datastore.domain.UserPreferencesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

const val TAG = "TOKEN"

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val validateEmailUseCase: ValidateEmailUseCase,
    private val validatePasswordUseCase: ValidatePasswordUseCase,
    private val authRepository: AuthRepository,
    private val userPreferencesRepository: UserPreferencesRepository
) : ViewModel() {
    private val _loginUiState = MutableStateFlow(LoginUiState())
    val loginUiState: StateFlow<LoginUiState> = _loginUiState.asStateFlow()
//    val authState: StateFlow<AuthState> = authRepository.authState

    private var errors = mutableMapOf<String, UiText?>()

    init {
        checkToken()
    }

    fun onEvent(event: LoginUiEvent) {
        when (event) {
            is LoginUiEvent.EmailChanged -> {
                _loginUiState.value = _loginUiState.value.copy(email = event.email)
                validateEmail()
            }

            is LoginUiEvent.PasswordChanged -> {
                _loginUiState.value = _loginUiState.value.copy(password = event.password)
                validatePassword()
            }

            is LoginUiEvent.FormSubmit -> {
                if (validateEmail() && validatePassword()) {
                    viewModelScope.launch {
                        authRepository.signIn(_loginUiState.value.toSignInDto()).collect {
                            when (it) {
                                is AuthState.Authenticated -> {
                                    if (it.data.error) {
                                        _loginUiState.value = _loginUiState.value.copy(
                                            authenticated = _loginUiState.value.authenticated.copy(
                                                error = it.data.error,
                                                message = it.data.message,
                                                isAuthenticated = !it.data.error,
                                                loading = false
                                            )
                                        )

                                    } else {
                                        _loginUiState.value = _loginUiState.value.copy(
                                            authenticated = _loginUiState.value.authenticated.copy(
                                                error = it.data.error,
                                                message = it.data.message,
                                                isAuthenticated = !it.data.error,
                                                loading = false
                                            )
                                        )
                                        it.data.accessToken?.let { token ->
                                            userPreferencesRepository.updateAccessToken(token)
                                        }
                                    }

                                }

                                is AuthState.Error -> {
                                    _loginUiState.value = _loginUiState.value.copy(
                                        authenticated = _loginUiState.value.authenticated.copy(
                                            error = true,
                                            message = it.error,
                                            isAuthenticated = false,
                                            loading = false
                                        )
                                    )
                                }

                                AuthState.Idle -> TODO()
                                AuthState.Loading -> {
                                    _loginUiState.value = _loginUiState.value.copy(
                                        authenticated = _loginUiState.value.authenticated.copy(
                                            loading = true,
                                            isAuthenticated = false
                                        )
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private fun validateEmail(): Boolean {
        val emailResult = validateEmailUseCase.execute(_loginUiState.value.email)
        errors["email"] = emailResult.errorMessage
        _loginUiState.value = _loginUiState.value.copy(errors = errors)
        return emailResult.success
    }

    private fun validatePassword(): Boolean {
        val passwordResult = validatePasswordUseCase.execute(_loginUiState.value.password)
        errors["password"] = passwordResult.errorMessage
        _loginUiState.value = _loginUiState.value.copy(errors = errors)
        return passwordResult.success
    }

    fun checkToken() {
        viewModelScope.launch {
            userPreferencesRepository.userPreferencesFlow.map {
                it.accessToken
            }.collectLatest { token ->
                Log.d(TAG, "checkToken: $token")
                if (token.isNotBlank() && token != null) {
                    _loginUiState.value = _loginUiState.value.copy(
                        authenticated = _loginUiState.value.authenticated.copy(
                            isAuthenticated = true,
                            message = "User Authenticated",
                            error = false,
                            loading = false
                        )
                    )
//                    _loginUiState.update {
//                        it.copy(
//                            authenticated = _loginUiState.value.authenticated.copy(
//                                isAuthenticated = true,
//                                message = "User Authenticated",
//                                error = false,
//                                loading = false
//                            )
//                        )
//                    }
                    return@collectLatest
                }
                _loginUiState.value = _loginUiState.value.copy(
                    authenticated = _loginUiState.value.authenticated.copy(
                        isAuthenticated = false,
                        message = "User Not Authenticated",
                        error = false,
                        loading = false
                    )
                )

            }
        }
    }

    private fun resetState() {
        _loginUiState.value = LoginUiState()
    }

}