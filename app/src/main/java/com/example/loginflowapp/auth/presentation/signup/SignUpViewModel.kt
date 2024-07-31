package com.example.loginflowapp.auth.presentation.signup

import androidx.lifecycle.ViewModel
import com.example.loginflowapp.auth.domain.UiText
import com.example.loginflowapp.auth.domain.ValidateEmailUseCase
import com.example.loginflowapp.auth.domain.ValidatePasswordUseCase
import com.example.loginflowapp.auth.domain.ValidateTextUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val validateTextUseCase: ValidateTextUseCase,
    private val validateEmailUseCase: ValidateEmailUseCase,
    private val validatePasswordUseCase: ValidatePasswordUseCase
) : ViewModel() {
    private var _signupUiState = MutableStateFlow(SignupUiState())
    val signupUiState: StateFlow<SignupUiState> = _signupUiState.asStateFlow()

    private val errors = mutableMapOf<String, UiText?>()

    fun update(signupState: SignupUiState) {
        _signupUiState.value = signupState
        validateFirstName()
        validateLastName()
        validateEmail()
        validatePassword()
    }

    fun submitSignUpForm() {
        if (validateFirstName() && validateLastName() && validateEmail() && validatePassword()) {

        }
    }

    private fun validateFirstName(): Boolean {
        val firstNameResult = validateTextUseCase.execute(_signupUiState.value.firstName)
        errors["firstName"] = firstNameResult.errorMessage
        _signupUiState.value = _signupUiState.value.copy(errors = errors)
        return firstNameResult.success
    }

    private fun validateLastName(): Boolean {
        val lastNameResult = validateTextUseCase.execute(_signupUiState.value.lastName)
        errors["lastName"] = lastNameResult.errorMessage
        _signupUiState.value = _signupUiState.value.copy(errors = errors)
        return lastNameResult.success
    }

    private fun validateEmail(): Boolean {
        val emailResult = validateEmailUseCase.execute(_signupUiState.value.email)
        errors["email"] = emailResult.errorMessage
        _signupUiState.value = _signupUiState.value.copy(errors = errors)
        return emailResult.success
    }

    private fun validatePassword(): Boolean {
        val passwordResult = validatePasswordUseCase.execute(_signupUiState.value.password)
        errors["password"] = passwordResult.errorMessage
        _signupUiState.value = _signupUiState.value.copy(errors = errors)
        return passwordResult.success
    }

}