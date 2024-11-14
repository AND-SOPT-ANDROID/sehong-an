package org.sopt.and.ui.screen.signUp.contract

import org.sopt.and.utils.UiEffect
import org.sopt.and.utils.UiEvent
import org.sopt.and.utils.UiState

class SignUpContract {
    data class State(
        val isLoading: Boolean = false
    ) : UiState

    sealed class Event : UiEvent {
        data class SignUpButtonClicked(
            val username: String,
            val password: String,
            val hobby: String
        ) : Event()
    }

    sealed class Effect : UiEffect {
        object NavigateToSignIn : Effect()
        data class ShowSuccessMessage(val message: String) : Effect()
        data class ShowErrorMessage(val message: String) : Effect()
    }
}