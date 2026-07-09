package com.exhale.chat_start.ui.screens.splash

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.exhale.chat_start.ui.navigation.ScreenRoutes
import com.exhale.chat_start.viewmodel.GameViewModel

@Composable
fun SplashScreen(navController: NavController, gameViewModel: GameViewModel = viewModel()) {
    LaunchedEffect(key1 = true) {
        val success = gameViewModel.prepareGame()
        if (success) {
            navController.navigate(ScreenRoutes.Login.route) {
                popUpTo(ScreenRoutes.Splash.route) { inclusive = true }
            }
        } else {
            navController.navigate(ScreenRoutes.Login.route) {
                popUpTo(ScreenRoutes.Splash.route) { inclusive = true }
            }
        }
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        AsyncImage(
            model = "https://images.stockcake.com/public/7/e/8/7e8ce333-8827-4447-8ab4-3c9790bd9673_large/noir-detective-mystery-stockcake.jpg",
            contentDescription = "App Logo",
            contentScale = ContentScale.Crop,
            modifier = Modifier.size(250.dp)
        )
        CircularProgressIndicator(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 50.dp)
        )
    }
}
