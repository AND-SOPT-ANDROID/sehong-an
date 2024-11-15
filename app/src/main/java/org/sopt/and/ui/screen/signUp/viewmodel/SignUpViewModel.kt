package org.sopt.and.ui.screen.signUp.viewmodel

import android.content.Context
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import org.sopt.and.R
import org.sopt.and.data.repository.DataStoreRepository
import org.sopt.and.data.repository.UserRepository
import org.sopt.and.network.adapter.ApiResult
import org.sopt.and.network.model.request.SignUpRequest
import org.sopt.and.ui.screen.signUp.contract.SignUpContract
import org.sopt.and.utils.BaseViewModel
import org.sopt.and.utils.isValidPassword
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val dataStoreRepository: DataStoreRepository,
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
                        username = event.username,
                    )
                }
            }
        }
    }

    private suspend fun userSignUp(username: String, password: String, hobby: String) {
        updateState(currentState.copy(isLoading = true))
        userRepository.signUp(SignUpRequest(username, password, hobby)).collect { result ->
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

    /** Email 입력값 */
    var usernameInput by mutableStateOf("")
        private set

    /** Password 입력값 */
    var passwordInput by mutableStateOf("")
        private set

    /** hobby 입력값 */
    var hobbyInput by mutableStateOf("")
        private set

    /** 회원가입 가능 여부 */
    val isEnabled by derivedStateOf {
        isUsernameValid && isPasswordValid && usernameInput.isNotEmpty() && passwordInput.isNotEmpty()
    }

    /** Email Valid 여부 */
    var isUsernameValid by mutableStateOf(true)
        private set

    /** Password Valid 여부 */
    var isPasswordValid by mutableStateOf(true)
        private set

    /** Password Valid 여부 */
    var isHobbyValid by mutableStateOf(true)
        private set

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

    fun onUsernameInputChange(value: String) {
        usernameInput = value
        validateEmail()
    }

    fun onPasswordInputChange(value: String) {
        passwordInput = value
        validatePassword()
    }

    fun onHobbyInputChange(value: String) {
        hobbyInput = value
        validateHobby()
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
        isUsernameValid = usernameInput.isNotEmpty() && usernameInput.length <= 8
    }

    private fun validatePassword() {
        isPasswordValid = passwordInput.isNotEmpty() && isValidPassword(passwordInput)
        signUpPasswordDescription =
            if (isPasswordValid) context.getString(R.string.sign_up_password_default) else context.getString(
                R.string.sign_up_password_error1
            )
    }

    private fun validateHobby() {
        isHobbyValid = hobbyInput.isNotEmpty() && hobbyInput.length <= 8
    }

}
