package org.sopt.and.ui.screen.signIn.viewmodel

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import dagger.hilt.android.lifecycle.HiltViewModel
import org.sopt.and.data.UserManager
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val userManager: UserManager
) : ViewModel() {
    var userIdInput by mutableStateOf("")
        private set
    var passwordInput by mutableStateOf("")
        private set
    var showDialog by mutableStateOf(false)
        private set

    fun onUserIdInputChange(newInput: String) {
        userIdInput = newInput
    }

    fun onPasswordInputChange(newInput: String) {
        passwordInput = newInput
    }

    fun handleLoginClick(navController: NavHostController, context: Context) {
        if (userIdInput.isEmpty() || passwordInput.isEmpty()) {
            showDialog = true
            return
        }
        val login = userManager.loginUser(userIdInput, passwordInput)
        if (login) {
            Toast.makeText(context, "로그인 성공", Toast.LENGTH_SHORT).show()
            userManager.setLoggedIn(true)  // 자동 로그인 설정
            navController.navigate("my") {
                popUpTo(navController.graph.startDestinationId) {
                    inclusive = true
                }
                launchSingleTop = true
            }
        } else {
            showDialog = true
        }
    }

    fun dismissDialog() {
        showDialog = false
    }
}
