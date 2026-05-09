package com.exhale.chat_start.ui.navigation

sealed class ScreenRoutes(val route: String) {
    object Splash : ScreenRoutes("splash")
    object Login : ScreenRoutes("login")
    object Register : ScreenRoutes("register")
    object Home : ScreenRoutes("home")
    object Profile : ScreenRoutes("profile")
    object Chat : ScreenRoutes("chat")
    object Stats : ScreenRoutes("stats")
    object Transition : ScreenRoutes("transition")
    object Ending : ScreenRoutes("ending")
}