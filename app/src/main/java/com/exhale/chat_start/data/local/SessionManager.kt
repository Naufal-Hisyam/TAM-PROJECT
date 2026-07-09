package com.exhale.chat_start.data.local

import android.content.Context
import android.content.SharedPreferences
import com.exhale.chat_start.data.model.GameProgress

class SessionManager(context: Context) {
    private val prefs: SharedPreferences = context.getSharedPreferences("chat_game_prefs", Context.MODE_PRIVATE)

    fun registerUser(username: String, passwordHash: String) {
        prefs.edit().putString("USER_$username", passwordHash).apply()
    }

    fun getPasswordForUser(username: String): String? {
        return prefs.getString("USER_$username", null)
    }

    fun saveActiveSession(username: String) {
        prefs.edit().putString("ACTIVE_USER", username).apply()
    }

    fun getActiveUser(): String? {
        return prefs.getString("ACTIVE_USER", null)
    }

    fun clearSession() {
        prefs.edit().remove("ACTIVE_USER").apply()
    }

    fun saveGameProgress(username: String, nodeId: String, pt: Int, ec: Int, fs: Int, pd: Int) {
        prefs.edit()
            .putString("NODE_$username", nodeId)
            .putInt("PT_$username", pt)
            .putInt("EC_$username", ec)
            .putInt("FS_$username", fs)
            .putInt("PD_$username", pd)
            .apply()
    }

    fun getGameProgress(username: String): GameProgress? {
        val nodeId = prefs.getString("NODE_$username", null) ?: return null
        val pt = prefs.getInt("PT_$username", 0)
        val ec = prefs.getInt("EC_$username", 0)
        val fs = prefs.getInt("FS_$username", 0)
        val pd = prefs.getInt("PD_$username", 0)
        return GameProgress(nodeId, pt, ec, fs, pd)
    }

    fun clearGameProgress(username: String) {
        prefs.edit()
            .remove("NODE_$username")
            .remove("PT_$username")
            .remove("EC_$username")
            .remove("FS_$username")
            .remove("PD_$username")
            .apply()
    }
}