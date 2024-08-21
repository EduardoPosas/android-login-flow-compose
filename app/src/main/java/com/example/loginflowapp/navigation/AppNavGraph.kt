package com.example.loginflowapp.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.loginflowapp.ui.HomeScreen

fun NavGraphBuilder.appFlow(
    navController: NavHostController
) {
    composable(route = AppRouter.Home.route) {
        HomeScreen() {
            navController.navigate("login_graph") {
                popUpTo(route = AppRouter.Home.route) {
                    inclusive = true
                }
            }
        }
    }
}