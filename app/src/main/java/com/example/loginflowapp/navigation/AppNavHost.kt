package com.example.loginflowapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController

@Composable
fun AppNavHost(
    navController: NavHostController = rememberNavController()
) {
    NavHost(navController = navController, startDestination = "login_graph") {
        navigation(route = "login_graph", startDestination = LoginRouter.Login.route) {
            loginFlow(navController = navController)
        }
        navigation(route = "app_graph", startDestination = AppRouter.Home.route) {
            appFlow(navController = navController)
        }
    }
}