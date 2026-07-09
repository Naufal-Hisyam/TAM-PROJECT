package com.exhale.chat_start.data.repository

import android.content.Context
import android.content.SharedPreferences

class AuthRepository(context: Context) {
    private val prefs: SharedPreferences = context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)

    fun login(email: String, pass: String): Boolean {
        val storedPass = prefs.getString(email, null)
        return storedPass != null && storedPass == pass
    }

    fun register(email: String, pass: String): Boolean {
        if (prefs.contains(email)) return false
        prefs.edit().putString(email, pass).apply()
        return true
    }

    fun logout() {
        // Logika logout lokal jika diperlukan
    }

    fun getCurrentUserEmail(): String? = null
    
    fun isUserLoggedIn(): Boolean = false
}
