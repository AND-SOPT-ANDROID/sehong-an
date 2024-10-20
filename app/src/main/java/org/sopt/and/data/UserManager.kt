package org.sopt.and.data

import javax.inject.Inject

class UserManager @Inject constructor(private val preferencesManager: PreferencesManager) {
    companion object {
        private const val IS_LOGGED_IN = "IS_LOGGED_IN"
        private const val USERNAME = "USERNAME"
        private const val PASSWORD = "PASSWORD"
    }

    fun registerUser(username: String, password: String) {
        preferencesManager.setValue(USERNAME, username)
        preferencesManager.setValue(PASSWORD, password)
    }

    fun loginUser(username: String, password: String): Boolean {
        val storedUsername = preferencesManager.getValue(USERNAME)
        val storedPassword = preferencesManager.getValue(PASSWORD)
        return storedUsername == username && storedPassword == password
    }

    fun setLoggedIn(loggedIn: Boolean) {
        preferencesManager.setBoolean(IS_LOGGED_IN, loggedIn)
    }

    fun isLoggedIn(): Boolean {
        return preferencesManager.getBoolean(IS_LOGGED_IN)
    }

    fun getUserEmail(): String? {
        return preferencesManager.getValue(USERNAME)
    }

    fun logoutUser() {
        setLoggedIn(false)
    }
}
