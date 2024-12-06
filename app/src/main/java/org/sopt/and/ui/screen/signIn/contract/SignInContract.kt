package org.sopt.and.ui.screen.signIn.contract

import org.sopt.and.ui.utils.UiEffect
import org.sopt.and.ui.utils.UiEvent
import org.sopt.and.ui.utils.UiState

class SignInContract {
    data class State(
        val isLoading: Boolean = false
    ) : UiState

    sealed class Event : UiEvent {
        data class SignInButtonClicked(
            val username: String,
            val password: String,
        ) : Event()
    }

    sealed class Effect : UiEffect {
        object NavigateToMyScreen : Effect()
        data class ShowSuccessMessage(val message: String) : Effect()
        data class ShowErrorMessage(val message: String) : Effect()
    }
}