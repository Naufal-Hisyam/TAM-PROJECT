package com.exhale.chat_start.ui.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.exhale.chat_start.ui.screens.auth.LoginScreen
import com.exhale.chat_start.ui.screens.auth.RegisterScreen
import com.exhale.chat_start.ui.screens.chat.ChatScreen
import com.exhale.chat_start.ui.screens.ending.EndingScreen
import com.exhale.chat_start.ui.screens.home.HomeScreen
import com.exhale.chat_start.ui.screens.profile.ProfileScreen
import com.exhale.chat_start.ui.screens.splash.SplashScreen
import com.exhale.chat_start.ui.screens.stats.IriStatsScreen
import com.exhale.chat_start.ui.screens.transition.DecisionResultView
import com.exhale.chat_start.viewmodel.AuthViewModel
import com.exhale.chat_start.viewmodel.GameViewModel

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val authViewModel: AuthViewModel = viewModel()
    val gameViewModel: GameViewModel = viewModel()

    NavHost(navController = navController, startDestination = ScreenRoutes.Splash.route) {
        composable(ScreenRoutes.Splash.route) {
            SplashScreen(navController)
        }
        composable(ScreenRoutes.Login.route) {
            LoginScreen(navController, authViewModel)
        }
        composable(ScreenRoutes.Register.route) {
            RegisterScreen(navController, authViewModel)
        }
        composable(ScreenRoutes.Home.route) {
            HomeScreen(navController, authViewModel, gameViewModel)
        }
        composable(ScreenRoutes.Profile.route) {
            ProfileScreen(navController, authViewModel, gameViewModel)
        }
        composable(ScreenRoutes.Chat.route) {
            ChatScreen(navController, gameViewModel)
        }
        composable(ScreenRoutes.Stats.route) {
            IriStatsScreen(navController, gameViewModel)
        }
        composable(ScreenRoutes.Transition.route) {
            DecisionResultView(navController, gameViewModel)
        }
        composable(ScreenRoutes.Ending.route) {
            EndingScreen(navController, gameViewModel)
        }
    }
}