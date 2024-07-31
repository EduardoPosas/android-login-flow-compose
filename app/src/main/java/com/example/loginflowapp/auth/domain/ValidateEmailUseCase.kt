package com.example.loginflowapp.auth.domain

import android.util.Patterns
import com.example.loginflowapp.R
import javax.inject.Inject

class ValidateEmailUseCase @Inject constructor() : ValidationBaseUseCase<String, ValidationResult> {
    override fun execute(input: String): ValidationResult {
        if (input.isBlank()) {
            return ValidationResult(
                success = false,
                errorMessage = UiText.StringResource(resId = R.string.email_cannot_be_blank)
            )
        }
        if (!isEmailValid(input)) {
            return ValidationResult(
                success = false,
                errorMessage = UiText.StringResource(resId = R.string.invalid_email)
            )
        }
        return ValidationResult(
            success = true,
            errorMessage = null
        )
    }
}

fun isEmailValid(email: String): Boolean {
    return Patterns.EMAIL_ADDRESS.matcher(email).matches()
}