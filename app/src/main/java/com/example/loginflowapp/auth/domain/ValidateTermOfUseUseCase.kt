package com.example.loginflowapp.auth.domain

import com.example.loginflowapp.R
import javax.inject.Inject

class ValidateTermOfUseUseCase @Inject constructor() :
    ValidationBaseUseCase<Boolean, ValidationResult> {
    override fun execute(input: Boolean): ValidationResult {
        if (!input) {
            return ValidationResult(
                success = false,
                errorMessage = UiText.StringResource(R.string.must_accept_term_of_use)
            )
        }
        return ValidationResult(
            success = true,
            errorMessage = null
        )
    }
}