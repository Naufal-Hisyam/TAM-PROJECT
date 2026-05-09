package com.exhale.chat_start.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.exhale.chat_start.data.local.SessionManager
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

class GameViewModel(application: Application) : AndroidViewModel(application) {

    private val sessionManager = SessionManager(application)
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

    fun checkProgress() {
        val user = sessionManager.getActiveUser()
        if (user != null) {
            val progress = sessionManager.getGameProgress(user)
            if (progress != null) {
                _hasSavedProgress.value = true
                _iriStats.value = IriStats(progress.pt, progress.ec, progress.fs, progress.pd)
            } else {
                _hasSavedProgress.value = false
                _iriStats.value = IriStats()
            }
        }
    }

    fun startNewGame() {
        val user = sessionManager.getActiveUser() ?: return
        sessionManager.clearGameProgress(user)
        _iriStats.value = IriStats()
        _chatHistory.value = emptyList()
        sessionManager.saveGameProgress(user, Constants.STARTING_NODE_ID, 0, 0, 0, 0)
        checkProgress()
        loadNode(Constants.STARTING_NODE_ID)
    }

    fun continueGame() {
        val user = sessionManager.getActiveUser() ?: return
        val progress = sessionManager.getGameProgress(user)
        if (progress != null) {
            _iriStats.value = IriStats(progress.pt, progress.ec, progress.fs, progress.pd)
            _chatHistory.value = emptyList()
            loadNode(progress.nodeId)
        }
    }

    fun finishGame() {
        val user = sessionManager.getActiveUser()
        if (user != null) {
            sessionManager.clearGameProgress(user)
            _hasSavedProgress.value = false
        }
    }

    private fun loadNode(nodeId: String) {
        val node = repository.getNode(nodeId)
        node?.let {
            viewModelScope.launch {
                _isTyping.value = true
                _currentNode.value = null
                delay(Constants.TYPING_DELAY_MS)
                _isTyping.value = false

                _chatHistory.update { currentList ->
                    currentList + Message(text = it.npcText, isFromUser = false)
                }
                _currentNode.value = it
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

        val user = sessionManager.getActiveUser()
        if (user != null) {
            sessionManager.saveGameProgress(
                username = user,
                nodeId = choice.nextNodeId,
                pt = _iriStats.value.pt,
                ec = _iriStats.value.ec,
                fs = _iriStats.value.fs,
                pd = _iriStats.value.pd
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