package org.sopt.and.presentation.ui.signIn

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import org.sopt.and.data.repository.DataStoreRepository
import org.sopt.and.data.repository.UserRepository
import org.sopt.and.network.adapter.ApiResult
import org.sopt.and.network.model.request.LoginRequest
import org.sopt.and.presentation.utils.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val dataStoreRepository: DataStoreRepository,
    @ApplicationContext private val context: Context
) : BaseViewModel<SignInContract.State, SignInContract.Event, SignInContract.Effect>(
    initialState = SignInContract.State()
) {
    override fun reduceState(event: SignInContract.Event) {
        viewModelScope.launch {
            when (event) {
                is SignInContract.Event.SignInButtonClicked -> {
                    if (event.username.isEmpty() || event.password.isEmpty()) {
                        showDialog = true
//                        postEffect(SignInContract.Effect.ShowErrorMessage("아이디와 비밀번호를 모두 입력해주세요."))
                    } else {
                        viewModelScope.launch {
                            userLogin(event.username, event.password)
                        }
                    }
                }
            }
        }
    }

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

    fun dismissDialog() {
        showDialog = false
    }

    private suspend fun userLogin(username: String, password: String) {
        updateState(currentState.copy(isLoading = true))
        userRepository.login(LoginRequest(username, password)).collect { result ->
            when (result) {
                is ApiResult.Success -> {
                    val accessToken = result.data?.result?.token ?: ""
                    if (accessToken.isNotEmpty()) {
                        dataStoreRepository.setAccessToken(accessToken)
                        postEffect(SignInContract.Effect.ShowSuccessMessage("로그인 성공"))
                    } else {
                        postEffect(SignInContract.Effect.ShowErrorMessage("로그인 실패: 토큰을 받지 못했습니다."))
                    }
                }

                is ApiResult.ApiError -> {
                    postEffect(SignInContract.Effect.ShowErrorMessage("API 에러: ${result.message}"))
                }

                is ApiResult.NetworkError -> {
                    postEffect(SignInContract.Effect.ShowErrorMessage("네트워크 에러: ${result.throwable.message}"))
                }
            }
            updateState(currentState.copy(isLoading = false))
        }

    }
}
