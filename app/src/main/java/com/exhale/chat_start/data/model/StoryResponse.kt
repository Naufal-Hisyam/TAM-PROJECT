package com.exhale.chat_start.data.model

import com.google.gson.annotations.SerializedName

data class StoryResponse(
    @SerializedName("story_nodes")
    val storyNodes: List<StoryNode>
)
