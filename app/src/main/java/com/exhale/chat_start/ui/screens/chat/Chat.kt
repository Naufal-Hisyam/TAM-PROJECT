package com.exhale.chat_start.ui.screens.chat

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
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

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Investigation") },
                actions = {
                    Button(
                        onClick = { navController.navigate(ScreenRoutes.Stats.route) },
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color.Transparent),
                        elevation = null
                    ) {
                        Text("IRI Stats", color = Color.White)
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            LazyColumn(
                state = listState,
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
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
                    if (node.choices.isNotEmpty()) {
                        item {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(8.dp),
                                verticalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                node.choices.forEach { choice ->
                                    Button(
                                        onClick = { gameViewModel.makeChoice(choice) },
                                        modifier = Modifier.fillMaxWidth()
                                    ) {
                                        Text(text = choice.text)
                                    }
                                }
                            }
                        }
                    } else {
                        item {
                            Button(
                                onClick = { navController.navigate(ScreenRoutes.Ending.route) },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp),
                                colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.secondary)
                            ) {
                                Text("Finish Investigation")
                            }
                        }
                    }
                }
            }
        }
    }
}