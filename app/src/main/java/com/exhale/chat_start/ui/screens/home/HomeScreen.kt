package com.exhale.chat_start.ui.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.exhale.chat_start.ui.navigation.ScreenRoutes
import com.exhale.chat_start.viewmodel.AuthViewModel
import com.exhale.chat_start.viewmodel.GameViewModel

@Composable
fun HomeScreen(navController: NavController, authViewModel: AuthViewModel, gameViewModel: GameViewModel) {
    val hasSavedProgress by gameViewModel.hasSavedProgress.collectAsState()

    LaunchedEffect(Unit) {
        gameViewModel.checkProgress()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "The Quiet Entelechy",
            style = MaterialTheme.typography.h4,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colors.secondary
        )
        Spacer(modifier = Modifier.height(32.dp))

        if (hasSavedProgress) {
            Button(
                onClick = {
                    gameViewModel.continueGame()
                    navController.navigate(ScreenRoutes.Chat.route)
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Continue")
            }
        } else {
            Button(
                onClick = {
                    gameViewModel.startNewGame()
                    navController.navigate(ScreenRoutes.Chat.route)
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("New Game")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = { navController.navigate(ScreenRoutes.Profile.route) },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Investigator Profile")
        }
    }
}