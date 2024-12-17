package org.sopt.and.presentation.ui.signUp

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import org.sopt.and.R
import org.sopt.and.data.network.adapter.ApiResult
import org.sopt.and.data.network.model.request.SignUpRequest
import org.sopt.and.domain.repository.UserRepository
import org.sopt.and.presentation.utils.BaseViewModel
import org.sopt.and.utils.isValidPassword
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val userRepository: UserRepository,
    @ApplicationContext private val context: Context
) : BaseViewModel<SignUpContract.State, SignUpContract.Event, SignUpContract.Effect>(
    initialState = SignUpContract.State()
) {
    override fun reduceState(event: SignUpContract.Event) {
        viewModelScope.launch {
            when (event) {
                is SignUpContract.Event.SignUpButtonClicked -> {
                    userSignUp(
                        hobby = event.hobby,
                        password = event.password,
                        userId = event.userId,
                    )
                }
            }
        }
    }

    private suspend fun userSignUp(userId: String, password: String, hobby: String) {
        updateState(currentState.copy(isLoading = true))
        userRepository.signUp(SignUpRequest(userId, password, hobby)).collect { result ->
            when (result) {
                is ApiResult.Success -> {
                    postEffect(SignUpContract.Effect.ShowSuccessMessage("회원가입 성공"))
                }

                is ApiResult.ApiError -> {
                    postEffect(SignUpContract.Effect.ShowErrorMessage("API 에러: ${result.message}"))
                }

                is ApiResult.NetworkError -> {
                    postEffect(SignUpContract.Effect.ShowErrorMessage("네트워크 에러: ${result.throwable.message}"))
                }
            }
            updateState(currentState.copy(isLoading = false))
        }

    }


    /** Email 의 Description */
    var signUpEmailDescription by mutableStateOf(context.getString(R.string.sign_up_username))
        private set

    /** Password 의 Description */
    var signUpPasswordDescription by mutableStateOf(context.getString(R.string.sign_up_password_default))
        private set


    /** Email 초기 포커스 한번은 무시하기 */
    var hasFocusUsernameChanged by mutableStateOf(false)
        private set

    /** Password 초기 포커스 한번은 무시하기 */
    var hasFocusPasswordChanged by mutableStateOf(false)
        private set

    /** hobby 초기 포커스 한번은 무시하기 */
    var hasFocusHobbyChanged by mutableStateOf(false)
        private set


    fun updateId(userId: String) {
        updateState(currentState.copy(userId = userId))
        validateEmail()
        checkSingUpEnable()
    }

    fun updatePassword(password: String) {
        updateState(currentState.copy(password = password))
        validatePassword()
        checkSingUpEnable()
    }

    fun updateHobby(hobby: String) {
        updateState(currentState.copy(hobby = hobby))
        validateHobby()
        checkSingUpEnable()
    }

    private fun checkSingUpEnable() {
        val isSignUpEnabled =
            currentState.isUserIdValid && currentState.isPasswordValid && currentState.isHobbyValid
        updateState(currentState.copy(isSignUpEnabled = isSignUpEnabled))
    }

    fun onEmailFocusChange(isFocused: Boolean) {
        if (hasFocusUsernameChanged && !isFocused) {
            validateEmail()
        }
        hasFocusUsernameChanged = true
    }

    fun onPasswordFocusChange(isFocused: Boolean) {
        if (hasFocusPasswordChanged && !isFocused) {
            validatePassword()
        }
        hasFocusPasswordChanged = true
    }

    fun onHobbyFocusChange(isFocused: Boolean) {
        if (hasFocusHobbyChanged && !isFocused) {
            validateHobby()
        }
        hasFocusHobbyChanged = true
    }


    private fun validateEmail() {
        val isUserIdValid = currentState.userId.isNotEmpty() && currentState.userId.length <= 8
        updateState(currentState.copy(isUserIdValid = isUserIdValid))
    }

    private fun validatePassword() {
        val isPasswordValid =
            currentState.password.isNotEmpty() && isValidPassword(currentState.password)
        updateState(currentState.copy(isPasswordValid = isPasswordValid))
        signUpPasswordDescription =
            if (isPasswordValid) context.getString(R.string.sign_up_password_default) else context.getString(
                R.string.sign_up_password_error1
            )
    }

    private fun validateHobby() {
        val isHobbyValid = currentState.hobby.isNotEmpty() && currentState.hobby.length <= 8
        updateState(currentState.copy(isHobbyValid = isHobbyValid))
    }
}
