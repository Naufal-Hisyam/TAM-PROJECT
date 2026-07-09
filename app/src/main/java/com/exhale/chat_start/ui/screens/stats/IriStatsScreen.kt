package com.exhale.chat_start.ui.screens.stats

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.exhale.chat_start.viewmodel.GameViewModel

@Composable
fun IriStatsScreen(navController: NavController, gameViewModel: GameViewModel) {
    val stats by gameViewModel.iriStats.collectAsState()

    val backgroundGradient = Brush.verticalGradient(
        colors = listOf(
            MaterialTheme.colors.background,
            MaterialTheme.colors.primaryVariant.copy(alpha = 0.3f),
            MaterialTheme.colors.background
        )
    )

    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = Color.Transparent,
                contentColor = Color.White,
                elevation = 0.dp,
                title = { Text("DOSSIER: PSYCH PROFILE", letterSpacing = 2.sp) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { paddingValues ->
        Box(modifier = Modifier
            .fillMaxSize()
            .background(backgroundGradient)
            .padding(paddingValues)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp),
                verticalArrangement = Arrangement.Top
            ) {
                Text(
                    text = "INTERPERSONAL REACTIVITY INDEX",
                    style = MaterialTheme.typography.overline,
                    color = MaterialTheme.colors.secondary,
                    letterSpacing = 2.sp
                )
                Text(
                    text = "AGENT METRICS",
                    style = MaterialTheme.typography.h4.copy(fontWeight = FontWeight.ExtraBold),
                    color = Color.White
                )
                
                Spacer(modifier = Modifier.height(32.dp))

                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    backgroundColor = MaterialTheme.colors.surface.copy(alpha = 0.6f),
                    elevation = 4.dp
                ) {
                    Column(modifier = Modifier.padding(20.dp)) {
                        StatItem("PERSPECTIVE TAKING", "PT", stats.pt, MaterialTheme.colors.primary)
                        Divider(modifier = Modifier.padding(vertical = 16.dp), color = Color.White.copy(alpha = 0.05f))
                        StatItem("EMPATHIC CONCERN", "EC", stats.ec, MaterialTheme.colors.secondary)
                        Divider(modifier = Modifier.padding(vertical = 16.dp), color = Color.White.copy(alpha = 0.05f))
                        StatItem("FANTASY SCALE", "FS", stats.fs, Color(0xFF8E44AD))
                        Divider(modifier = Modifier.padding(vertical = 16.dp), color = Color.White.copy(alpha = 0.05f))
                        StatItem("PERSONAL DISTRESS", "PD", stats.pd, Color(0xFFC0392B))
                    }
                }
                
                Spacer(modifier = Modifier.height(32.dp))
                
                Text(
                    text = "* These metrics fluctuate based on real-time investigation decisions. High values in specific areas may unlock unique directive branches.",
                    style = MaterialTheme.typography.caption,
                    color = Color.White.copy(alpha = 0.4f),
                    lineHeight = 16.sp
                )
            }
        }
    }
}

@Composable
fun StatItem(label: String, short: String, value: Int, color: Color) {
    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = label,
                style = MaterialTheme.typography.subtitle2,
                color = Color.White.copy(alpha = 0.7f)
            )
            Text(
                text = value.toString(),
                style = MaterialTheme.typography.h6.copy(fontWeight = FontWeight.Bold),
                color = color
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        // Progress bar simulation
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(6.dp)
                .background(Color.White.copy(alpha = 0.1f), RoundedCornerShape(3.dp))
        ) {
            val progress = (value.toFloat() / 20f).coerceIn(0.1f, 1f)
            Box(
                modifier = Modifier
                    .fillMaxWidth(progress)
                    .fillMaxHeight()
                    .background(color, RoundedCornerShape(3.dp))
            )
        }
    }
}
