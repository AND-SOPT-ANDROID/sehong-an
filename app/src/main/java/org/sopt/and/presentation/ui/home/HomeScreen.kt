package org.sopt.and.presentation.ui.home

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import org.sopt.and.presentation.components.lazyRow.AutoSlidingImagePager
import org.sopt.and.presentation.components.lazyRow.CategoryLazyRow
import org.sopt.and.presentation.components.lazyRow.EditorSelectImageLazyList
import org.sopt.and.presentation.components.lazyRow.TopTwentyImageList
import org.sopt.and.presentation.theme.WavveTheme

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(
    navController: NavHostController,
    modifier: Modifier,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    HomeScreenContent(
        modifier = modifier,
        imageOverviews = uiState.imageOverviews,
        categories = uiState.categories,
        imageItemsType1 = uiState.imageItemsType1,
        imageItemsType2 = uiState.imageItemsType2
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreenContent(
    modifier: Modifier,
    imageOverviews: List<ImageOverviewViewState>,
    categories: List<String>,
    imageItemsType1: List<Int>,
    imageItemsType2: List<Int>
) {
    val pagerState = rememberPagerState(
        initialPage = 0,
        initialPageOffsetFraction = 0f,
        pageCount = { imageOverviews.size }
    )

    LazyColumn(
        modifier = modifier
            .background(WavveTheme.colors.gray_1)
    ) {
        stickyHeader {
            CategoryLazyRow(categories)
        }
        item {
            AutoSlidingImagePager(
                state = pagerState,
                imageOverviews = imageOverviews,
                onImageClicked = { imageOverview ->
                    Log.d("HomeScreen", "Image clicked: ${imageOverview.title}")
                },
                modifier = Modifier.fillMaxWidth(),
            )
        }
        item {
            EditorSelectImageLazyList(imageItemsType1)
        }
        item {
            TopTwentyImageList(imageItemsType2)
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewHomeScreen() {
    // 네비게이션 컨트롤러를 미리 보기용으로 생성합니다.
    val navController = rememberNavController()

    // HomeScreen을 호출하며 필요한 파라미터를 제공합니다.
    Box(modifier = Modifier.fillMaxSize()) {
        HomeScreen(navController = navController, modifier = Modifier.fillMaxSize())
    }
}