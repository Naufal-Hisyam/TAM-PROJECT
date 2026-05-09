package com.exhale.chat_start.data.model

data class StoryNode(
    val id: String,
    val npcText: String,
    val choices: List<Choice>
)