package org.sopt.and.presentation.ui.signIn

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.sopt.and.data.network.adapter.ApiResult
import org.sopt.and.data.network.model.request.LoginRequest
import org.sopt.and.domain.repository.DataStoreRepository
import org.sopt.and.domain.repository.UserRepository
import org.sopt.and.presentation.utils.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val dataStoreRepository: DataStoreRepository,
) : BaseViewModel<SignInContract.State, SignInContract.Event, SignInContract.Effect>(
    initialState = SignInContract.State()
) {
    override fun reduceState(event: SignInContract.Event) {
        viewModelScope.launch {
            when (event) {
                is SignInContract.Event.SignInButtonClicked -> {
                    if (event.username.isEmpty() || event.password.isEmpty()) {
                        updateDialogStatus(true)
                    } else {
                        viewModelScope.launch {
                            userLogin(event.username, event.password)
                        }
                    }
                }
            }
        }
    }

    fun updateDialogStatus(isDialogShown: Boolean) {
        updateState(currentState.copy(isDialogShown = isDialogShown))
    }

    fun updateId(userId: String) {
        updateState(currentState.copy(userId = userId))
    }

    fun updatePassword(password: String) {
        updateState(currentState.copy(password = password))
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
