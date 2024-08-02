package com.example.loginflowapp.auth.presentation.login

import android.util.Log
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
import com.example.loginflowapp.components.form.FormEmailTextField
import com.example.loginflowapp.components.form.FormHeadingText
import com.example.loginflowapp.components.form.FormHorizontalOrDivider
import com.example.loginflowapp.components.form.FormIconButton
import com.example.loginflowapp.components.form.FormPasswordTextField
import com.example.loginflowapp.components.form.FormPrimaryButton
import com.example.loginflowapp.components.text.UnderlinedSmallText
import com.example.loginflowapp.ui.theme.LoginFlowAppTheme

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    onNavigateToSignup: () -> Unit = {}
) {

    val loginViewModel: LoginViewModel = hiltViewModel()
    val loginUiState by loginViewModel.loginUiState.collectAsStateWithLifecycle()
    Log.d("INPUT_VALIDATION", loginUiState.toString())
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp),
    ) {
        FormBodyText(text = "Login")
        FormHeadingText(text = "Welcome Back")
        Spacer(modifier = Modifier.height(48.dp))
        FormEmailTextField(
            label = "Email",
            value = loginUiState.email,
            isError = loginUiState.errors["email"] != null,
            errorText = loginUiState.errors["email"]
        ) {
//            loginViewModel.update(loginUiState.copy(email = it))
            loginViewModel.onEvent(LoginUiEvent.EmailChanged(it))
        }
        Spacer(modifier = Modifier.height(8.dp))
        FormPasswordTextField(
            label = "Password",
            value = loginUiState.password,
            isError = loginUiState.errors["password"] != null,
            errorText = loginUiState.errors["password"]
        ) {
//            loginViewModel.update(loginUiState.copy(password = it))
            loginViewModel.onEvent(LoginUiEvent.PasswordChanged(it))
        }
        Spacer(modifier = Modifier.height(24.dp))
        UnderlinedSmallText(
            text = "Forgot your password",
            modifier.align(Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.height(48.dp))
        FormPrimaryButton(buttonText = "Login")
        Spacer(modifier = Modifier.height(32.dp))
        FormHorizontalOrDivider()
        Spacer(modifier = Modifier.height(32.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                FormIconButton(image = R.drawable.google_signup_logo) {}
                FormIconButton(image = R.drawable.google_signup_logo) {}
            }
        }
        Spacer(modifier = Modifier.height(32.dp))
        FormBottomLabel(
            noClickableText = "Don't you have an account yet?",
            clickableText = "Register",
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            onNavigateToSignup()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    LoginFlowAppTheme {
        LoginScreen()
    }
}