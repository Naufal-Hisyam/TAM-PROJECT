package com.exhale.chat_start.data.repository

import android.content.Context
import com.exhale.chat_start.data.local.SessionManager
import com.exhale.chat_start.data.model.User

class AuthRepository(context: Context) {
    private val sessionManager = SessionManager(context)
    private var currentUser: User? = null

    fun register(username: String, password: String): Boolean {
        if (sessionManager.getPasswordForUser(username) != null) {
            return false
        }
        sessionManager.registerUser(username, password)
        return true
    }

    fun login(username: String, password: String): Boolean {
        val storedPassword = sessionManager.getPasswordForUser(username)
        if (storedPassword == password) {
            sessionManager.saveActiveSession(username)
            currentUser = User(username = username, isRegistered = true)
            return true
        }
        return false
    }

    fun logout() {
        sessionManager.clearSession()
        currentUser = null
    }
}