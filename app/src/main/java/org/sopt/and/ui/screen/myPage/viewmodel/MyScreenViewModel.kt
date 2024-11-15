package org.sopt.and.ui.screen.myPage.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.sopt.and.data.repository.DataStoreRepository
import org.sopt.and.data.repository.UserRepository
import org.sopt.and.network.adapter.ApiResult
import org.sopt.and.ui.screen.myPage.contract.MyPageContract
import org.sopt.and.utils.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class MyScreenViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val dataStoreRepository: DataStoreRepository,
    @ApplicationContext private val context: Context
) : BaseViewModel<MyPageContract.State, MyPageContract.Event, MyPageContract.Effect>(
    initialState = MyPageContract.State()
) {
    private val _hobby = MutableStateFlow<String>("")
    val hobby: StateFlow<String> = _hobby.asStateFlow()

    override fun reduceState(event: MyPageContract.Event) {
        viewModelScope.launch {
            when (event) {
                is MyPageContract.Event.LogoutClicked -> {
                    logout()
                }
            }
        }
    }

    init {
        viewModelScope.launch {
            val token = dataStoreRepository.getAccessToken()
            if (token.isNotEmpty()) {
                fetchHobbies(token)
            }
        }
    }

    private fun fetchHobbies(token: String) {
        viewModelScope.launch {
            updateState(currentState.copy(isLoading = true))
            userRepository.getHobby(token).collect { result ->
                when (result) {
                    is ApiResult.Success -> {
                        Log.e("result", "${result.data}")
                        val hobby = result.data?.result?.hobby ?: ""
                        if (hobby.isNotEmpty()) {
                            Log.e("hobby", hobby)
                            _hobby.value = hobby
                        } else {
                            postEffect(MyPageContract.Effect.ShowErrorMessage("취미 불러오기 실패."))
                        }
                    }

                    is ApiResult.ApiError -> {}

                    is ApiResult.NetworkError -> {}
                }
                updateState(currentState.copy(isLoading = false))
            }
        }
    }

    fun logout() {
        dataStoreRepository.deleteTokens()
        postEffect(MyPageContract.Effect.NavigateToLogin)
    }
}