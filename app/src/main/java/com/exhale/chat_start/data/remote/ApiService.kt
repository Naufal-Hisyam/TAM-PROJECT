package com.exhale.chat_start.data.remote

import com.exhale.chat_start.data.model.StoryResponse
import retrofit2.http.GET

interface ApiService {
    @GET("RDSaputraaa/0584de6241f0e0babfd831d249c22b8e/raw/d0d19c493cc8f506a984afb3357f381bfb791ec3/chat")
    suspend fun getStoryData(): StoryResponse
}
