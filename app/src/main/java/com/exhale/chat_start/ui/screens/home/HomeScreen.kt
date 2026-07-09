package com.exhale.chat_start.ui.screens.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.exhale.chat_start.ui.navigation.ScreenRoutes
import com.exhale.chat_start.viewmodel.AuthViewModel
import com.exhale.chat_start.viewmodel.GameViewModel

@Composable
fun HomeScreen(navController: NavController, authViewModel: AuthViewModel, gameViewModel: GameViewModel) {
    val hasSavedProgress by gameViewModel.hasSavedProgress.collectAsState()

    LaunchedEffect(Unit) {
        gameViewModel.checkProgress()
    }

    Box(modifier = Modifier.fillMaxSize()) {
        AsyncImage(
            model = "https://images.stockcake.com/public/7/e/8/7e8ce333-8827-4447-8ab4-3c9790bd9673_large/noir-detective-mystery-stockcake.jpg",
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            Color.Black.copy(alpha = 0.5f),
                            Color.Black.copy(alpha = 0.9f)
                        )
                    )
                )
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(top = 60.dp)
            ) {
                Text(
                    text = "THE QUIET",
                    style = MaterialTheme.typography.h6,
                    color = MaterialTheme.colors.secondary,
                    letterSpacing = 8.sp
                )
                Text(
                    text = "ENTELECHY",
                    style = MaterialTheme.typography.h2.copy(
                        fontWeight = FontWeight.ExtraBold,
                        lineHeight = 50.sp
                    ),
                    color = Color.White,
                    textAlign = TextAlign.Center
                )
                Divider(
                    modifier = Modifier
                        .width(60.dp)
                        .padding(vertical = 16.dp),
                    color = MaterialTheme.colors.secondary,
                    thickness = 2.dp
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 40.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                if (hasSavedProgress) {
                    MenuButton(
                        text = "CONTINUE CASE",
                        icon = Icons.Default.Refresh,
                        onClick = {
                            gameViewModel.continueGame()
                            navController.navigate(ScreenRoutes.Chat.route)
                        }
                    )
                }

                MenuButton(
                    text = "NEW INVESTIGATION",
                    icon = Icons.Default.List,
                    onClick = {
                        gameViewModel.startNewGame()
                        navController.navigate(ScreenRoutes.Chat.route)
                    }
                )

                MenuButton(
                    text = "AGENT PROFILE",
                    icon = Icons.Default.Person,
                    onClick = { navController.navigate(ScreenRoutes.Profile.route) },
                    isSecondary = true
                )
                
                TextButton(
                    onClick = { 
                        authViewModel.logout()
                        navController.navigate(ScreenRoutes.Login.route) {
                            popUpTo(0) { inclusive = true }
                        }
                    },
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                ) {
                    Text("ABANDON MISSION", color = Color.White.copy(alpha = 0.5f), fontSize = 12.sp)
                }
            }
        }
    }
}

@Composable
fun MenuButton(
    text: String,
    icon: ImageVector,
    onClick: () -> Unit,
    isSecondary: Boolean = false
) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp),
        shape = RoundedCornerShape(12.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = if (isSecondary) Color.Transparent else MaterialTheme.colors.primary,
            contentColor = Color.White
        ),
        border = if (isSecondary) BorderStroke(1.dp, Color.White.copy(alpha = 0.5f)) else null,
        elevation = ButtonDefaults.elevation(defaultElevation = if (isSecondary) 0.dp else 4.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Icon(icon, contentDescription = null, modifier = Modifier.size(20.dp))
            Spacer(modifier = Modifier.width(12.dp))
            Text(
                text = text,
                fontWeight = FontWeight.Bold,
                letterSpacing = 2.sp
            )
        }
    }
}
