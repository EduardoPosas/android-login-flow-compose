package com.example.loginflowapp.auth.presentation.login

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.loginflowapp.auth.domain.UiText
import com.example.loginflowapp.auth.domain.ValidateEmailUseCase
import com.example.loginflowapp.auth.domain.ValidatePasswordUseCase
import com.example.loginflowapp.auth.domain.model.AuthState
import com.example.loginflowapp.auth.domain.repository.AuthRepository
import com.example.loginflowapp.datastore.domain.UserPreferencesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class LoginViewModel @Inject constructor(
    private val validateEmailUseCase: ValidateEmailUseCase,
    private val validatePasswordUseCase: ValidatePasswordUseCase,
    private val authRepository: AuthRepository,
    private val userPreferencesRepository: UserPreferencesRepository
) : ViewModel() {
    private val _loginUiState = MutableStateFlow(LoginUiState())
    val loginUiState: StateFlow<LoginUiState> = _loginUiState.asStateFlow()

    private var errors = mutableMapOf<String, UiText?>()

    init {
        isValidAccessToken()
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
                        authRepository.signIn(event.signIn).collect {
                            when (it) {
                                is AuthState.Success -> {
                                    if (it.data.error) {
                                        // Update state based on error = true
                                        _loginUiState.update { currentState ->
                                            currentState.copy(
                                                authenticated = currentState.authenticated.copy(
                                                    message = it.data.message,
                                                    error = it.data.error,
                                                    isAuthenticated = !it.data.error,
                                                )
                                            )
                                        }
                                    } else {
                                        // update the state based on error = false
                                        _loginUiState.update { currentState ->
                                            currentState.copy(
                                                authenticated = currentState.authenticated.copy(
                                                    error = it.data.error,
                                                    message = it.data.message,
                                                    isAuthenticated = !it.data.error
                                                )
                                            )
                                        }
                                        // add access token to proto datastore
                                        it.data.accessToken?.let { token ->
                                            authRepository.updateAccessToken(token)
                                            Log.d("TOKEN", "Token updated!")
                                        }
                                    }

                                }

                                is AuthState.Error -> {
                                    _loginUiState.update { currentState ->
                                        currentState.copy(
                                            authenticated = currentState.authenticated.copy(
                                                error = true,
                                                message = it.error,
                                                isAuthenticated = false,
                                            )
                                        )
                                    }
                                }

                                is AuthState.Loading -> {
                                    _loginUiState.update { currentState ->
                                        currentState.copy(
                                            authenticated = currentState.authenticated.copy(
                                                loading = it.isLoading
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

    private fun isValidAccessToken() {
        viewModelScope.launch {
            // get token
            userPreferencesRepository.userPreferencesFlow.collectLatest {
                Log.d("TOKEN", it.accessToken)
                if (it.accessToken.isNotBlank()) {
                    _loginUiState.update { currentState ->
                        currentState.copy(
                            authenticated = currentState.authenticated.copy(
                                isAuthenticated = true,
                                loading = false,
                                error = false,
                                message = "Access token valid!"
                            )
                        )
                    }
                }
            }
        }
    }
}