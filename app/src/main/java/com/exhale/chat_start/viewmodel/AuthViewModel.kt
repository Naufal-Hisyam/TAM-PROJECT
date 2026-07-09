package com.exhale.chat_start.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import com.exhale.chat_start.data.repository.AuthRepository

class AuthViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = AuthRepository(application)

    private val _username = MutableStateFlow(repository.getCurrentUserEmail() ?: "")
    val username: StateFlow<String> = _username.asStateFlow()

    private val _authError = MutableStateFlow<String?>(null)
    val authError: StateFlow<String?> = _authError.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    fun login(email: String, pass: String, onSuccess: () -> Unit) {
        if (repository.login(email, pass)) {
            _username.value = email
            _authError.value = null
            onSuccess()
        } else {
            _authError.value = "Login failed. Check your email or password."
        }
    }

    fun register(email: String, pass: String, onSuccess: () -> Unit) {
        if (email.contains("@") && pass.length >= 6) {
            if (repository.register(email, pass)) {
                _authError.value = null
                onSuccess()
            } else {
                _authError.value = "Email already registered."
            }
        } else {
            _authError.value = "Invalid email format or password too short (min 6)."
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
