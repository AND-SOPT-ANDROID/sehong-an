package org.sopt.and.ui.screen.home.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import org.sopt.and.R
import javax.inject.Inject

data class ImageOverviewViewState(
    val imageResId: Int, // 로컬 이미지 리소스 ID를 저장
    val title: String
)

@HiltViewModel
class HomeViewModel @Inject constructor() : ViewModel() {

    // 로컬 이미지 목록을 관리하는 상태
    private val _imageOverviews = MutableLiveData(
        listOf(
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
    )
    val imageOverviews: LiveData<List<ImageOverviewViewState>> = _imageOverviews

    // 카테고리 목록
    private val _categories = MutableLiveData(
        listOf("뉴클래식", "드라마", "예능", "영화", "애니", "해외시리즈", "시사교양", "키즈")
    )
    val categories: LiveData<List<String>> = _categories

    // 이미지 리스트들 관리
    private val _imageItemsType1 = MutableLiveData(
        List(5) { R.drawable.wavve_image1 }
    )
    val imageItemsType1: LiveData<List<Int>> = _imageItemsType1

    private val _imageItemsType2 = MutableLiveData(
        List(5) { R.drawable.wavve_image1 }
    )
    val imageItemsType2: LiveData<List<Int>> = _imageItemsType2
}