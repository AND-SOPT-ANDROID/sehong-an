package org.sopt.and.presentation.ui.home

import org.sopt.and.presentation.utils.UiEffect
import org.sopt.and.presentation.utils.UiEvent
import org.sopt.and.presentation.utils.UiState


class HomeContract {
    data class State(
        val isLoading: Boolean = false,
        val categories: List<String> = emptyList(),
        val imageOverviews: List<ImageOverviewViewState> = emptyList(),
        val imageItemsType1: List<Int> = emptyList(),
        val imageItemsType2: List<Int> = emptyList(),
    ) : UiState

    sealed class Event : UiEvent {
        data class ComponentClicked(
            val id: Int
        ) : Event() // 특정 이미지 클릭시 특정 화면으로 이동(미구현)

        data class CategoryClicked(
            val category: String
        ) : Event()
    }

    sealed class Effect : UiEffect {
        data object NavigateToSearch : Effect()
        data object NavigateTo : Effect()
        data class ShowErrorMessage(val message: String) : Effect()  // 에러 메시지 표시
    }
}