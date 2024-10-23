package org.sopt.and.ui.screen.signUp.viewmodel

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import org.sopt.and.R
import org.sopt.and.data.UserManager
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val userManager: UserManager,
    @ApplicationContext private val context: Context
) : ViewModel() {
    /** Email 입력값 */
    var userIdInput by mutableStateOf("")
        private set

    /** Password 입력값 */
    var passwordInput by mutableStateOf("")
        private set

    /** 회원가입 가능 여부 */
    var isEnabled by mutableStateOf(false)
        private set

    /** Email Valid 여부 */
    var isEmailValid by mutableStateOf(true)
        private set

    /** Password Valid 여부 */
    var isPasswordValid by mutableStateOf(true)
        private set

    /** Email 의 Description */
    var signUpEmailDescription by mutableStateOf(context.getString(R.string.sign_up_email_default))
        private set

    /** Password 의 Description */
    var signUpPasswordDescription by mutableStateOf(context.getString(R.string.sign_up_password_default))
        private set

    /** Email 초기 포커스 한번은 무시하기 */
    var hasFocusEmailChanged by mutableStateOf(false)
        private set

    /** Password 초기 포커스 한번은 무시하기 */
    var hasFocusPasswordChanged by mutableStateOf(false)
        private set

    fun onUserIdInputChange(value: String) {
        userIdInput = value
        validateEmail()
        updateIsEnabled()
    }

    fun onPasswordInputChange(value: String) {
        passwordInput = value
        validatePassword()
        updateIsEnabled()
    }

    fun onEmailFocusChange(isFocused: Boolean) {
        if (hasFocusEmailChanged && !isFocused) {
            validateEmail()
        }
        hasFocusEmailChanged = true
    }

    fun onPasswordFocusChange(isFocused: Boolean) {
        if (hasFocusPasswordChanged && !isFocused) {
            validatePassword()
        }
        hasFocusPasswordChanged = true
    }

    private fun validateEmail() {
        isEmailValid = userIdInput.isNotEmpty() && isValidEmail(userIdInput)
        signUpEmailDescription = when {
            userIdInput.isEmpty() -> context.getString(R.string.sign_up_email_default)
            userIdInput.length < 5 -> context.getString(R.string.sign_up_email_error1)
            !isEmailValid -> context.getString(R.string.sign_up_email_error2)
            else -> context.getString(R.string.sign_up_email_default)
        }
    }

    private fun validatePassword() {
        isPasswordValid = passwordInput.isNotEmpty() && isValidPassword(passwordInput)
        signUpPasswordDescription =
            if (isPasswordValid) context.getString(R.string.sign_up_password_default) else context.getString(
                R.string.sign_up_password_error1
            )
    }

    private fun updateIsEnabled() {
        isEnabled =
            isEmailValid && isPasswordValid && userIdInput.isNotEmpty() && passwordInput.isNotEmpty()
    }

    fun registerUser() {
        userManager.registerUser(userIdInput, passwordInput)
    }

    private fun isValidEmail(email: String): Boolean {
        // Email 검증 로직 추가
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    private fun isValidPassword(password: String): Boolean {
        // Password 검증 로직 추가 (예: 8자 이상)
        return password.length >= 8
    }
}
