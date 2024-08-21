package com.example.loginflowapp.navigation

sealed class LoginRouter(val route: String) {
    data object Login : LoginRouter("login")
    data object Register : LoginRouter("register")
    data object Privacy : LoginRouter("privacy")
    data object TermOfUse : LoginRouter("terms")
}

sealed class AppRouter(val route: String) {
    data object Home : AppRouter(route = "home")
}