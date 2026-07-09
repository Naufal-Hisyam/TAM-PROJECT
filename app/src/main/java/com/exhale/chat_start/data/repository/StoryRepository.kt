package com.exhale.chat_start.data.repository

import com.exhale.chat_start.data.model.StoryNode
import com.exhale.chat_start.data.remote.ApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class StoryRepository {
    private val apiService: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl("https://gist.githubusercontent.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

    private var storyCache: Map<String, StoryNode>? = null

    suspend fun loadStory(): Boolean {
        return try {
            val response = apiService.getStoryData()
            storyCache = response.storyNodes.associateBy { it.id }
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    fun getNode(nodeId: String): StoryNode? {
        return storyCache?.get(nodeId)
    }
}
