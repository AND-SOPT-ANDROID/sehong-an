package org.sopt.and.data

import android.content.Context
import android.content.SharedPreferences

class PreferencesManager(context: Context) {
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE)
    companion object {
        private const val IS_LOGGED_IN = "IS_LOGGED_IN"
        private const val USERNAME = "USERNAME"
        private const val PASSWORD = "PASSWORD"
    }

    // 회원가입 시 아이디와 비밀번호를 저장
    fun registerUser(username: String, password: String) {
        val editor = sharedPreferences.edit()
        editor.putString(USERNAME, username)
        editor.putString(PASSWORD, password)
        editor.apply()
    }
    fun getUsername(): String? {
        return sharedPreferences.getString(USERNAME, null)
    }

    // 로그인 시 아이디와 비밀번호가 일치하는지 확인
    fun loginUser(username: String, password: String): Boolean {
        val storedUsername = sharedPreferences.getString(USERNAME, null)
        val storedPassword = sharedPreferences.getString(PASSWORD, null)

        return storedUsername == username && storedPassword == password
    }

    // 로그인 상태 저장
    fun setLoggedIn(loggedIn: Boolean) {
        val editor = sharedPreferences.edit()
        editor.putBoolean(IS_LOGGED_IN, loggedIn)
        editor.apply()
    }

    // 로그인 상태 확인
    fun isLoggedIn(): Boolean {
        return sharedPreferences.getBoolean(IS_LOGGED_IN, false)
    }

    // 로그아웃 시 로그인 상태 및 저장된 데이터 초기화
    fun logoutUser() {
        val editor = sharedPreferences.edit()
        editor.clear()
        editor.apply()
    }
}
