package com.example.loginflowapp.auth.domain

import com.example.loginflowapp.R
import javax.inject.Inject

class ValidateTextUseCase @Inject constructor() : ValidationBaseUseCase<String, ValidationResult> {
    override fun execute(input: String): ValidationResult {
        if (input.isBlank()) {
            return ValidationResult(
                success = false,
                errorMessage = UiText.StringResource(R.string.required_field)
            )
        }
        if (input.length < 4) {
            return ValidationResult(
                success = false,
                errorMessage = UiText.StringResource(R.string.must_be_at_least_4_characteres)
            )
        }
        return ValidationResult(
            success = true,
            errorMessage = null
        )
    }
}