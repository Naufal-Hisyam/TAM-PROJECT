package com.exhale.chat_start.ui.screens.chat

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Info
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import com.exhale.chat_start.ui.navigation.ScreenRoutes
import com.exhale.chat_start.ui.screens.chat.components.ChatBubble
import com.exhale.chat_start.ui.screens.chat.components.TypingIndicator
import com.exhale.chat_start.viewmodel.GameViewModel
import kotlinx.coroutines.flow.collectLatest

@Composable
fun ChatScreen(navController: NavController, gameViewModel: GameViewModel) {
    val chatHistory by gameViewModel.chatHistory.collectAsState()
    val currentNode by gameViewModel.currentNode.collectAsState()
    val isTyping by gameViewModel.isTyping.collectAsState()
    val listState = rememberLazyListState()

    LaunchedEffect(Unit) {
        gameViewModel.navigateToTransition.collectLatest { shouldNavigate ->
            if (shouldNavigate) {
                navController.navigate(ScreenRoutes.Transition.route)
            }
        }
    }

    LaunchedEffect(chatHistory.size, isTyping, currentNode) {
        val totalItems = chatHistory.size + if (isTyping) 1 else 0 + if (currentNode != null) 1 else 0
        if (totalItems > 0) {
            listState.animateScrollToItem(totalItems)
        }
    }

    val backgroundGradient = Brush.verticalGradient(
        colors = listOf(
            MaterialTheme.colors.background,
            MaterialTheme.colors.surface
        )
    )

    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = MaterialTheme.colors.background,
                contentColor = Color.White,
                elevation = 8.dp,
                title = {
                    Column {
                        Text(
                            text = "INVESTIGATION IN PROGRESS",
                            style = MaterialTheme.typography.overline,
                            color = MaterialTheme.colors.secondary
                        )
                        Text(
                            text = "CASE: THE QUIET ENTELECHY",
                            style = MaterialTheme.typography.subtitle2.copy(fontWeight = FontWeight.Bold),
                            fontSize = 12.sp
                        )
                    }
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                actions = {
                    IconButton(onClick = { navController.navigate(ScreenRoutes.Stats.route) }) {
                        Icon(
                            imageVector = Icons.Default.Info,
                            contentDescription = "Stats",
                            tint = MaterialTheme.colors.secondary
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(backgroundGradient)
        ) {
            Column(modifier = Modifier.fillMaxSize()) {
                LazyColumn(
                    state = listState,
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth(),
                    contentPadding = PaddingValues(bottom = 16.dp, top = 8.dp),
                    verticalArrangement = Arrangement.Top
                ) {
                    items(chatHistory) { message ->
                        ChatBubble(text = message.text, isFromUser = message.isFromUser)
                    }

                    if (isTyping) {
                        item {
                            TypingIndicator()
                        }
                    }

                    currentNode?.let { node ->
                        if (node.choices.isEmpty()) {
                            item {
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(24.dp),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Button(
                                        onClick = { navController.navigate(ScreenRoutes.Ending.route) },
                                        modifier = Modifier.fillMaxWidth().height(50.dp),
                                        shape = RoundedCornerShape(12.dp),
                                        colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.secondary)
                                    ) {
                                        Text("FINALIZE REPORT", fontWeight = FontWeight.Bold, letterSpacing = 1.sp)
                                    }
                                }
                            }
                        }
                    }
                }

                // Action Area for Choices
                currentNode?.let { node ->
                    if (node.choices.isNotEmpty()) {
                        Surface(
                            modifier = Modifier.fillMaxWidth(),
                            elevation = 16.dp,
                            color = MaterialTheme.colors.surface,
                            shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)
                        ) {
                            Column(
                                modifier = Modifier
                                    .padding(20.dp),
                                verticalArrangement = Arrangement.spacedBy(12.dp)
                            ) {
                                Text(
                                    text = "DIRECTIVE OPTIONS",
                                    style = MaterialTheme.typography.overline,
                                    color = MaterialTheme.colors.onSurface.copy(alpha = 0.5f),
                                    modifier = Modifier.padding(bottom = 4.dp)
                                )
                                node.choices.forEach { choice ->
                                    Button(
                                        onClick = { gameViewModel.makeChoice(choice) },
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .height(52.dp),
                                        shape = RoundedCornerShape(12.dp),
                                        elevation = ButtonDefaults.elevation(defaultElevation = 2.dp)
                                    ) {
                                        Text(
                                            text = choice.text.uppercase(),
                                            style = MaterialTheme.typography.button.copy(
                                                fontWeight = FontWeight.Bold,
                                                letterSpacing = 1.sp
                                            )
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
