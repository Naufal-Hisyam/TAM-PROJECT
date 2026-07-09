package com.exhale.chat_start.ui.screens.chat.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ChatBubble(text: String, isFromUser: Boolean) {
    val alignment = if (isFromUser) Alignment.CenterEnd else Alignment.CenterStart
    val bubbleShape = if (isFromUser) {
        RoundedCornerShape(16.dp, 16.dp, 2.dp, 16.dp)
    } else {
        RoundedCornerShape(16.dp, 16.dp, 16.dp, 2.dp)
    }
    
    val backgroundColor = if (isFromUser) MaterialTheme.colors.primary else MaterialTheme.colors.surface
    val textColor = if (isFromUser) Color.White else MaterialTheme.colors.onSurface
    val shadowColor = if (isFromUser) Color.Black.copy(alpha = 0.25f) else Color.Transparent

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp, horizontal = 12.dp),
        contentAlignment = alignment
    ) {
        Column(horizontalAlignment = if (isFromUser) Alignment.End else Alignment.Start) {
            Box(
                modifier = Modifier
                    .shadow(elevation = if (isFromUser) 4.dp else 0.dp, shape = bubbleShape)
                    .clip(bubbleShape)
                    .background(backgroundColor)
                    .widthIn(max = 280.dp)
                    .padding(horizontal = 16.dp, vertical = 10.dp)
            ) {
                Text(
                    text = text,
                    color = textColor,
                    style = MaterialTheme.typography.body1.copy(
                        fontSize = 15.sp,
                        lineHeight = 20.sp
                    )
                )
            }
        }
    }
}
