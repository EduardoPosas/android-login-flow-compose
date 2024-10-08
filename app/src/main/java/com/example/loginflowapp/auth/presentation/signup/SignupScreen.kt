package com.example.loginflowapp.auth.presentation.signup

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
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
    val context = LocalContext.current
    Log.d("SIGNUP", signupUiState.toString())

    LaunchedEffect(signupUiState.response.error, signupUiState.response.success) {
        if (!signupUiState.response.error && signupUiState.response.success) {
            Toast.makeText(
                context,
                signupUiState.response.message,
                Toast.LENGTH_LONG
            ).show()
            onNavigateToLogin()
        }
        if (signupUiState.response.error && !signupUiState.response.success) {
            Toast.makeText(
                context,
                signupUiState.response.message,
                Toast.LENGTH_LONG
            ).show()
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp)
            .verticalScroll(rememberScrollState()),
    ) {
        FormBodyText(text = "Hey there,")
        FormHeadingText(text = "Create an Account")
        Spacer(modifier = Modifier.height(48.dp))
        FormTextField(
            label = "First Name", value = signupUiState.firstName,
            isError = signupUiState.errors["firstName"] != null,
            errorText = signupUiState.errors["firstName"]
        ) {
//            signupViewModel.update(signupUiState.copy(firstName = it))
            signupViewModel.onEvent(SignupUiEvent.FirstNameChanged(it))
        }
        Spacer(modifier = Modifier.height(8.dp))
        FormTextField(
            label = "Last Name", value = signupUiState.lastName,
            isError = signupUiState.errors["lastName"] != null,
            errorText = signupUiState.errors["lastName"]
        ) {
//            signupViewModel.update(signupUiState.copy(lastName = it))
            signupViewModel.onEvent(SignupUiEvent.LastNameChanged(it))
        }
        Spacer(modifier = Modifier.height(8.dp))
        FormEmailTextField(
            label = "Email",
            value = signupUiState.email,
            isError = signupUiState.errors["email"] != null,
            errorText = signupUiState.errors["email"]
        )
        {
//            signupViewModel.update(signupUiState.copy(email = it))
            signupViewModel.onEvent(SignupUiEvent.EmailChanged(it))
        }
        Spacer(modifier = Modifier.height(8.dp))
        FormPasswordTextField(
            label = "Password",
            value = signupUiState.password,
            isError = signupUiState.errors["password"] != null,
            errorText = signupUiState.errors["password"]
        ) {
//            signupViewModel.update(signupUiState.copy(password = it))
            signupViewModel.onEvent(SignupUiEvent.PasswordChanged(it))
        }
        Spacer(modifier = Modifier.height(16.dp))
        FormConditionsCheckbox(
            checked = signupUiState.termsOfUse,
            onCheckedChange = {
//                signupViewModel.update(signupUiState.copy(termsOfUse = it))
                signupViewModel.onEvent(event = SignupUiEvent.TermsOfUseChanged(it))
            },
            onPrivacyClicked = {},
            onTermsClicked = {},
            isError = signupUiState.errors["termOfUse"] != null,
            errorText = signupUiState.errors["termOfUse"]
        )
        Spacer(modifier = Modifier.height(48.dp))
        FormPrimaryButton(buttonText = "Register") {
            signupViewModel.onEvent(SignupUiEvent.SignupSubmit(signupUiState.toSignUp()))
        }
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