package com.example.loginflowapp.auth.presentation.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.loginflowapp.auth.domain.UiText
import com.example.loginflowapp.auth.domain.ValidateEmailUseCase
import com.example.loginflowapp.auth.domain.ValidatePasswordUseCase
import com.example.loginflowapp.auth.domain.ValidateTermOfUseUseCase
import com.example.loginflowapp.auth.domain.ValidateTextUseCase
import com.example.loginflowapp.auth.domain.model.AuthState
import com.example.loginflowapp.auth.domain.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val validateTextUseCase: ValidateTextUseCase,
    private val validateEmailUseCase: ValidateEmailUseCase,
    private val validatePasswordUseCase: ValidatePasswordUseCase,
    private val validateTermOfUse: ValidateTermOfUseUseCase,
    private val authRepository: AuthRepository
) : ViewModel() {
    private var _signupUiState = MutableStateFlow(SignupUiState())
    val signupUiState: StateFlow<SignupUiState> = _signupUiState.asStateFlow()

    private val errors = mutableMapOf<String, UiText?>()

    fun update(signupState: SignupUiState) {
        _signupUiState.value = signupState
    }

    fun onEvent(event: SignupUiEvent) {
        when (event) {
            is SignupUiEvent.FirstNameChanged -> {
                _signupUiState.value = _signupUiState.value.copy(firstName = event.firstName)
                validateText("firstName", _signupUiState.value.firstName)
            }

            is SignupUiEvent.LastNameChanged -> {
                _signupUiState.value = _signupUiState.value.copy(lastName = event.lastName)
                validateText(field = "lastName", value = _signupUiState.value.lastName)
            }

            is SignupUiEvent.EmailChanged -> {
                _signupUiState.value = _signupUiState.value.copy(email = event.email)
                validateEmail()
            }

            is SignupUiEvent.PasswordChanged -> {
                _signupUiState.value = _signupUiState.value.copy(password = event.password)
                validatePassword()
            }

            is SignupUiEvent.TermsOfUseChanged -> {
                _signupUiState.value = _signupUiState.value.copy(termsOfUse = event.terms)
                validateTermOfUse()
            }

            is SignupUiEvent.SignupSubmit -> {
//                if (
//                    validateEmail()
//                    && validatePassword()
//                    && validateTermOfUse()
//                    && validateText(
//                        field = "lastName",
//                        value = _signupUiState.value.lastName
//                    )
//                    && validateText(
//                        field = "firstName",
//                        value = _signupUiState.value.firstName
//                    )
//                ) {
////                    viewModelScope.launch {
////                        authRepository.signUp(event.signUpDto).collect {
////                            when (it) {
////                                is AuthState.Authenticated -> {
////                                    _signupUiState.value = _signupUiState.value.copy(
////                                        response = _signupUiState.value.response.copy(
////                                            loading = false,
////                                            error = it.data.error,
////                                            message = it.data.message
////                                        )
////                                    )
////                                }
////
////                                is AuthState.Error -> {
////                                    _signupUiState.value = _signupUiState.value.copy(
////                                        response = _signupUiState.value.response.copy(
////                                            loading = false,
////                                            error = true,
////                                            message = it.error
////                                        )
////                                    )
////                                }
////
////                                AuthState.Idle -> {
////
////                                }
////
////                                AuthState.Loading -> {
////                                    _signupUiState.value = _signupUiState.value.copy(
////                                        response = _signupUiState.value.response.copy(
////                                            loading = true,
////                                            error = false,
////                                            message = ""
////                                        )
////                                    )
////                                }
////
////                                else -> {
////
////                                }
////                            }
////                        }
////                    }
//                }
            }

            else -> {}
        }
    }

    private fun validateText(field: String, value: String): Boolean {
        val textResult = validateTextUseCase.execute(value)
        errors[field] = textResult.errorMessage
        _signupUiState.value = _signupUiState.value.copy(errors = errors)
        return textResult.success
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

    private fun validateTermOfUse(): Boolean {
        val termsResult = validateTermOfUse.execute(_signupUiState.value.termsOfUse)
        errors["termOfUse"] = termsResult.errorMessage
        _signupUiState.value = _signupUiState.value.copy(errors = errors)
        return termsResult.success
    }

    fun resetSignUpState() {
        _signupUiState.value = SignupUiState()
    }

}