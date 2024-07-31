package com.example.loginflowapp.auth.presentation.login

import androidx.lifecycle.ViewModel
import com.example.loginflowapp.auth.domain.UiText
import com.example.loginflowapp.auth.domain.ValidateEmailUseCase
import com.example.loginflowapp.auth.domain.ValidatePasswordUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val validateEmailUseCase: ValidateEmailUseCase,
    private val validatePasswordUseCase: ValidatePasswordUseCase
) : ViewModel() {
    private val _loginUiState = MutableStateFlow(LoginUiState())
    val loginUiState: StateFlow<LoginUiState> = _loginUiState.asStateFlow()

    private var errors = mutableMapOf<String, UiText?>()

    fun update(loginState: LoginUiState) {
        _loginUiState.value = loginState
        validateEmail()
        validatePassword()
    }

    fun submitSignInForm() {
        if(validateEmail() && validatePassword()) {

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

}