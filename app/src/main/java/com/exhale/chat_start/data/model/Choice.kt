package com.exhale.chat_start.data.model

data class Choice(
    val text: String,
    val nextNodeId: String,
    val ptModifier: Int = 0,
    val ecModifier: Int = 0,
    val fsModifier: Int = 0,
    val pdModifier: Int = 0,
    val isMajorChoice: Boolean = false
)