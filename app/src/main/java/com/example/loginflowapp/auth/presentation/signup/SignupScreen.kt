package com.example.loginflowapp.auth.presentation.signup

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.loginflowapp.R
import com.example.loginflowapp.components.form.FormBodyText
import com.example.loginflowapp.components.form.FormBottomLabel
import com.example.loginflowapp.components.form.FormConditionsCheckbox
import com.example.loginflowapp.components.form.FormEmailTextField
import com.example.loginflowapp.components.form.FormHeadingText
import com.example.loginflowapp.components.form.FormHorizontalOrDivider
import com.example.loginflowapp.components.form.FormIconButton
import com.example.loginflowapp.components.form.FormPasswordTextField
import com.example.loginflowapp.components.form.FormPrimaryButton
import com.example.loginflowapp.components.form.FormTextField
import com.example.loginflowapp.ui.theme.LoginFlowAppTheme

@Composable
fun SignupScreen(
    modifier: Modifier = Modifier,
    onNavigateToLogin: () -> Unit = {}
) {

    val signupViewModel: SignUpViewModel = hiltViewModel()
    val signupUiState by signupViewModel.signupUiState.collectAsStateWithLifecycle()

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp),
    ) {
        FormBodyText(text = "Hey there,")
        FormHeadingText(text = "Create an Account")
        Spacer(modifier = Modifier.height(48.dp))
        FormTextField(label = "First Name", value = signupUiState.firstName) {
            signupViewModel.update(signupUiState.copy(firstName = it))
        }
        Spacer(modifier = Modifier.height(8.dp))
        FormTextField(label = "Last Name", value = signupUiState.lastName) {
            signupViewModel.update(signupUiState.copy(lastName = it))
        }
        Spacer(modifier = Modifier.height(8.dp))
        FormEmailTextField(label = "Email", value = signupUiState.email) {
            signupViewModel.update(signupUiState.copy(email = it))
        }
        Spacer(modifier = Modifier.height(8.dp))
        FormPasswordTextField(label = "Password", value = signupUiState.password) {
            signupViewModel.update(signupUiState.copy(password = it))
        }
        Spacer(modifier = Modifier.height(16.dp))
        FormConditionsCheckbox(
            checked = signupUiState.termsOfUse,
            onCheckedChange = {
                signupViewModel.update(signupUiState.copy(termsOfUse = it))
            },
            onPrivacyClicked = {},
            onTermsClicked = {}
        )
        Spacer(modifier = Modifier.height(48.dp))
        FormPrimaryButton(buttonText = "Register")
        Spacer(modifier = Modifier.height(32.dp))
        FormHorizontalOrDivider()
        Spacer(modifier = Modifier.height(32.dp))
        Box(
            modifier = modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                FormIconButton(image = R.drawable.google_signup_logo) {}
                FormIconButton(image = R.drawable.google_signup_logo) {}
            }
        }

        Spacer(modifier = Modifier.height(32.dp))
        FormBottomLabel(
            clickableText = "Login",
            noClickableText = "Already have an account",
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            onNavigateToLogin()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SignupScreenPreview() {
    LoginFlowAppTheme {
        SignupScreen()
    }
}