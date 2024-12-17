package org.sopt.and.presentation.ui.myPage

import android.content.Context
import android.util.Log
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import org.sopt.and.data.network.adapter.ApiResult
import org.sopt.and.domain.repository.DataStoreRepository
import org.sopt.and.domain.repository.UserRepository
import org.sopt.and.presentation.utils.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class MyScreenViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val dataStoreRepository: DataStoreRepository,
    @ApplicationContext private val context: Context
) : BaseViewModel<MyPageContract.State, MyPageContract.Event, MyPageContract.Effect>(
    initialState = MyPageContract.State()
) {
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
                fetchHobbies()
            }
        }
    }

    private fun updateHobby(hobby: String) {
        updateState(currentState.copy(hobby = hobby))
    }

    private fun fetchHobbies() {
        viewModelScope.launch {
            updateState(currentState.copy(isLoading = true))
            userRepository.getHobby().collect { result ->
                when (result) {
                    is ApiResult.Success -> {
                        Log.e("result", "${result.data}")
                        val hobby = result.data?.result?.hobby ?: ""
                        if (hobby.isNotEmpty()) {
                            Log.e("hobby", hobby)
                            updateHobby(hobby)
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