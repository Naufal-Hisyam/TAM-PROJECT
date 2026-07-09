package com.exhale.chat_start.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.exhale.chat_start.data.model.Choice
import com.exhale.chat_start.data.model.IriStats
import com.exhale.chat_start.data.model.Message
import com.exhale.chat_start.data.model.StoryNode
import com.exhale.chat_start.data.repository.StoryRepository
import com.exhale.chat_start.utils.Constants
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class GameViewModel : ViewModel() {

    private val repository = StoryRepository()

    private val _chatHistory = MutableStateFlow<List<Message>>(emptyList())
    val chatHistory: StateFlow<List<Message>> = _chatHistory.asStateFlow()

    private val _currentNode = MutableStateFlow<StoryNode?>(null)
    val currentNode: StateFlow<StoryNode?> = _currentNode.asStateFlow()

    private val _iriStats = MutableStateFlow(IriStats())
    val iriStats: StateFlow<IriStats> = _iriStats.asStateFlow()

    private val _isTyping = MutableStateFlow(false)
    val isTyping: StateFlow<Boolean> = _isTyping.asStateFlow()

    private val _navigateToTransition = MutableSharedFlow<Boolean>()
    val navigateToTransition: SharedFlow<Boolean> = _navigateToTransition.asSharedFlow()

    private val _hasSavedProgress = MutableStateFlow(false)
    val hasSavedProgress: StateFlow<Boolean> = _hasSavedProgress.asStateFlow()

    suspend fun prepareGame(): Boolean {
        return repository.loadStory()
    }

    fun checkProgress() {
        _hasSavedProgress.value = false
    }

    fun startNewGame() {
        _iriStats.value = IriStats()
        _chatHistory.value = emptyList()
        loadNode(Constants.STARTING_NODE_ID)
    }

    fun continueGame() {
        startNewGame()
    }

    fun finishGame() {
        _hasSavedProgress.value = false
    }

    private fun loadNode(nodeId: String) {
        val node = repository.getNode(nodeId)
        node?.let { storyNode ->
            viewModelScope.launch {
                _isTyping.value = true
                _currentNode.value = null
                delay(Constants.TYPING_DELAY_MS)
                _isTyping.value = false

                _chatHistory.update { currentList ->
                    currentList + Message(text = storyNode.npcText, isFromUser = false)
                }
                _currentNode.value = storyNode
            }
        }
    }

    fun makeChoice(choice: Choice) {
        _chatHistory.update { currentList ->
            currentList + Message(text = choice.text, isFromUser = true)
        }

        _iriStats.update { currentStats ->
            currentStats.copy(
                pt = currentStats.pt + choice.ptModifier,
                ec = currentStats.ec + choice.ecModifier,
                fs = currentStats.fs + choice.fsModifier,
                pd = currentStats.pd + choice.pdModifier
            )
        }

        if (choice.isMajorChoice) {
            viewModelScope.launch {
                _navigateToTransition.emit(true)
            }
        }

        _currentNode.value = null
        loadNode(choice.nextNodeId)
    }
}
