package org.sopt.and.ui.screen.myPage.contract

import org.sopt.and.ui.utils.UiEffect
import org.sopt.and.ui.utils.UiEvent
import org.sopt.and.ui.utils.UiState

class MyPageContract {
    data class State(
        val isLoading: Boolean = false,
        val authToken: String? = null,
        val hobby: String? = null
    ) : UiState

    sealed class Event : UiEvent {
        data object LogoutClicked : Event()
    }

    sealed class Effect : UiEffect {
        data object NavigateToLogin : Effect()
        data class ShowErrorMessage(val message: String) : Effect()  // 에러 메시지 표시
    }
}