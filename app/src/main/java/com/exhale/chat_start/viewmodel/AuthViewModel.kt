package com.exhale.chat_start.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.exhale.chat_start.data.repository.AuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class AuthViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = AuthRepository(application)

    private val _username = MutableStateFlow("")
    val username: StateFlow<String> = _username.asStateFlow()

    private val _authError = MutableStateFlow<String?>(null)
    val authError: StateFlow<String?> = _authError.asStateFlow()

    fun login(name: String, pass: String): Boolean {
        return if (repository.login(name, pass)) {
            _username.value = name
            _authError.value = null
            true
        } else {
            _authError.value = "Invalid username or password."
            false
        }
    }

    fun register(name: String, pass: String): Boolean {
        if (name.isBlank() || pass.isBlank()) {
            _authError.value = "Fields cannot be empty."
            return false
        }
        return if (repository.register(name, pass)) {
            _username.value = name
            _authError.value = null
            true
        } else {
            _authError.value = "Username already exists."
            false
        }
    }

    fun clearError() {
        _authError.value = null
    }

    fun logout() {
        repository.logout()
        _username.value = ""
    }
}