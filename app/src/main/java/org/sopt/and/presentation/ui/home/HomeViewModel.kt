package org.sopt.and.presentation.ui.home

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.sopt.and.R
import org.sopt.and.presentation.utils.BaseViewModel
import javax.inject.Inject

data class ImageOverviewViewState(
    val imageResId: Int, // 로컬 이미지 리소스 ID를 저장
    val title: String
)

@HiltViewModel
class HomeViewModel @Inject constructor(

) : BaseViewModel<HomeContract.State, HomeContract.Event, HomeContract.Effect>(
    initialState = HomeContract.State()
) {
    override fun reduceState(event: HomeContract.Event) {
        viewModelScope.launch {
            when (event) {
                is HomeContract.Event.CategoryClicked -> {
                    // 카테고리 클릭 이벤트 처리
                }

                is HomeContract.Event.ComponentClicked -> {
                    // 특정 이미지 클릭시 특정 화면으로 이동(미구현)
                }
            }
        }
    }
    init {
        updateState(currentState.copy(
            imageOverviews = listOf(
                ImageOverviewViewState(
                    imageResId = R.drawable.wavve_image1, // 로컬 리소스 이미지
                    title = "Image 1"
                ),
                ImageOverviewViewState(
                    imageResId = R.drawable.wavve_image1,
                    title = "Image 2"
                ),
                ImageOverviewViewState(
                    imageResId = R.drawable.wavve_image1,
                    title = "Image 3"
                )
            )
        ))
        updateState(currentState.copy(
            categories = listOf("뉴클래식", "드라마", "예능", "영화", "애니", "해외시리즈", "시사교양", "키즈")
        ))
        updateState(currentState.copy(
            imageItemsType1 = List(5) { R.drawable.wavve_image1 }
        ))
        updateState(currentState.copy(
            imageItemsType2 = List(5) { R.drawable.wavve_image1 }
        ))
    }
}