package org.sopt.and.ui.screen.home.composable

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
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import org.sopt.and.ui.components.lazyRow.AutoSlidingImagePager
import org.sopt.and.ui.components.lazyRow.CategoryLazyRow
import org.sopt.and.ui.components.lazyRow.EditorSelectImageLazyList
import org.sopt.and.ui.components.lazyRow.TopTwentyImageList
import org.sopt.and.ui.screen.home.viewmodel.HomeViewModel
import org.sopt.and.ui.screen.home.viewmodel.ImageOverviewViewState
import org.sopt.and.ui.theme.WavveTheme

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(
    navController: NavHostController,
    modifier: Modifier,
    viewModel: HomeViewModel = hiltViewModel()
) {
    // 뷰모델에서 데이터를 가져옵니다.
    val imageOverviews by viewModel.imageOverviews.observeAsState(emptyList())
    val categories by viewModel.categories.observeAsState(emptyList())
    val imageItemsType1 by viewModel.imageItemsType1.observeAsState(emptyList())
    val imageItemsType2 by viewModel.imageItemsType2.observeAsState(emptyList())

    HomeScreenContent(
        modifier = modifier,
        imageOverviews = imageOverviews,
        categories = categories,
        imageItemsType1 = imageItemsType1,
        imageItemsType2 = imageItemsType2
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