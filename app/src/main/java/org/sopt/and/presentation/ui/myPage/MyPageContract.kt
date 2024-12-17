package org.sopt.and.presentation.ui.myPage

import org.sopt.and.presentation.utils.UiEffect
import org.sopt.and.presentation.utils.UiEvent
import org.sopt.and.presentation.utils.UiState

class MyPageContract {
    data class State(
        val isLoading: Boolean = false,
        val authToken: String? = null,
        val hobby: String = ""
    ) : UiState

    sealed class Event : UiEvent {
        data object LogoutClicked : Event()
    }

    sealed class Effect : UiEffect {
        data object NavigateToLogin : Effect()
        data class ShowErrorMessage(val message: String) : Effect()  // 에러 메시지 표시
    }
}