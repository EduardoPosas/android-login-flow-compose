package com.example.loginflowapp.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.loginflowapp.auth.presentation.login.LoginScreen
import com.example.loginflowapp.auth.presentation.signup.SignupScreen

fun NavGraphBuilder.loginFlow(
    navController: NavHostController
) {
    composable(LoginRouter.Login.route) {
        LoginScreen(
            onNavigateToSignup = { navController.navigate(LoginRouter.Register.route) },
            onNavigateToHome = {
                navController.navigate("app_graph") {
                    popUpTo(route = LoginRouter.Login.route) {
                        inclusive = true
                    }
                }
            }
        )
    }
    composable(route = LoginRouter.Register.route) {
        SignupScreen() {
            navController.navigate(LoginRouter.Login.route) {
                popUpTo(route = LoginRouter.Login.route) {
                    inclusive = true
                }
            }
        }
    }
    composable(route = LoginRouter.Privacy.route) {

    }
    composable(route = LoginRouter.TermOfUse.route) {

    }
}