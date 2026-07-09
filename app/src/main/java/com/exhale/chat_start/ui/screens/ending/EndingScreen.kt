package com.exhale.chat_start.ui.screens.ending

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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.exhale.chat_start.ui.navigation.ScreenRoutes
import com.exhale.chat_start.viewmodel.GameViewModel

@Composable
fun EndingScreen(navController: NavController, gameViewModel: GameViewModel) {
    val stats by gameViewModel.iriStats.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Simulation Concluded",
            style = MaterialTheme.typography.h4,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colors.secondary,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = "Final Empathy Analysis",
            style = MaterialTheme.typography.h6
        )
        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "Perspective Taking: ${stats.pt}", style = MaterialTheme.typography.body1)
        Text(text = "Empathic Concern: ${stats.ec}", style = MaterialTheme.typography.body1)
        Text(text = "Fantasy Scale: ${stats.fs}", style = MaterialTheme.typography.body1)
        Text(text = "Personal Distress: ${stats.pd}", style = MaterialTheme.typography.body1)

        Spacer(modifier = Modifier.height(48.dp))

        Button(
            onClick = {
                gameViewModel.finishGame()
                navController.navigate(ScreenRoutes.Home.route) {
                    popUpTo(0) { inclusive = true }
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Return to Main Menu")
        }
    }
}