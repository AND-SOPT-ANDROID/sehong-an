package org.sopt.and.presentation.ui.signUp

import org.sopt.and.presentation.utils.UiEffect
import org.sopt.and.presentation.utils.UiEvent
import org.sopt.and.presentation.utils.UiState

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