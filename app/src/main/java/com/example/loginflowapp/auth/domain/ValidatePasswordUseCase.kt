package com.example.loginflowapp.auth.domain

import com.example.loginflowapp.R
import javax.inject.Inject

class ValidatePasswordUseCase @Inject constructor() :
    ValidationBaseUseCase<String, ValidationResult> {
    override fun execute(input: String): ValidationResult {
        if (input.isBlank()) {
            return ValidationResult(
                success = false,
                errorMessage = UiText.StringResource(R.string.password_cannot_be_blank)
            )
        }
        if (input.length < 8) {
            return ValidationResult(
                success = false,
                errorMessage = UiText.StringResource(R.string.must_be_at_least_8_characteres)
            )
        }
        if (!isPasswordValid(input)) {
            return ValidationResult(
                success = false,
                errorMessage = UiText.StringResource(R.string.must_contait_at_least_1_character_and_1_letter)
            )
        }
        return ValidationResult(
            success = true,
            errorMessage = null
        )
    }
}

fun isPasswordValid(password: String): Boolean {
    return password.any { it.isDigit() } &&
            password.any { it.isLetter() }
}