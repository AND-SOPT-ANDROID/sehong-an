package org.sopt.and.ui.screen.home.composable

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.delay
import org.sopt.and.ui.screen.home.viewmodel.HomeViewModel
import org.sopt.and.ui.screen.home.viewmodel.ImageOverviewViewState
import org.sopt.and.ui.theme.darkGray1

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

    val pagerState = rememberPagerState(
        initialPage = 0, // 첫 번째 페이지부터 시작
        initialPageOffsetFraction = 0f, // 페이지 오프셋 초기값 설정
        pageCount = { imageOverviews.size } // 전체 페이지 수를 이미지 목록 크기로 설정
    )
    LazyColumn(
        modifier = modifier
            .background(darkGray1)
    ) {
        stickyHeader {
            HorizontalCategoryList(categories)
        }
        item {
            AutoSlidingImagePager(
                modifier = Modifier.fillMaxWidth(),
                state = pagerState,
                imageOverviews = imageOverviews,
                onImageClicked = { imageOverview ->
                    println("Image clicked: ${imageOverview.title}")
                }
            )
        }
        item {
            ImageLazyListType1(imageItemsType1)
        }
        item {
            ImageLazyListType2(imageItemsType2)
        }
    }
}


@Composable
fun HorizontalCategoryList(categories: List<String>) {
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .background(darkGray1)
            .padding(vertical = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp) // 텍스트 사이의 간격을 설정합니다.
    ) {
        items(categories) { category ->
            CategoryItem(category = category)
        }
    }
}

@Composable
fun CategoryItem(category: String) {
    Text(
        text = category,
        color = Color.LightGray,
        fontSize = 18.sp,
        modifier = Modifier
            .padding(horizontal = 8.dp)
            .clickable {
                // 각 항목을 클릭했을 때의 동작을 정의합니다.
                Log.d("CategoryItem", "$category clicked")
            }
    )
}

@Composable
fun ImageLazyListType1(imageItems: List<Int>) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, bottom = 5.dp, top = 20.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "믿고 보는 웨이브 에디터 추천작",
            color = Color.White,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        Icon(
            imageVector = Icons.Default.ArrowForward,
            contentDescription = null,
            tint = Color.White
        )
    }
    LazyRow(
        modifier = Modifier
            .fillMaxSize()
    ) {
        items(imageItems) { imageRes ->
            ImageItemType1(imageRes = imageRes)
        }
    }
}

@Composable
fun ImageItemType1(imageRes: Int) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Image(
            painter = painterResource(id = imageRes),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .clip(RoundedCornerShape(8.dp)),
            contentScale = ContentScale.Crop
        )
    }
}

@Composable
fun ImageLazyListType2(imageItems: List<Int>) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, top = 5.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "오늘의 TOP 20",
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        }

        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
        ) {
            itemsIndexed(imageItems) { index, imageRes ->
                ImageItemType2(imageRes = imageRes, index = index + 1)
            }
        }
    }
}

@Composable
fun ImageItemType2(imageRes: Int, index: Int) {
    Box(
        modifier = Modifier
            .width(180.dp)
            .height(270.dp)
            .padding(8.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(Color.DarkGray)
    ) {
        Image(
            painter = painterResource(id = imageRes),
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        Text(
            text = index.toString(),
            color = Color.White,
            fontSize = 80.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(start = 8.dp)
        )
    }
}

@Composable
fun AutoSlidingImagePager(
    modifier: Modifier = Modifier,
    state: PagerState,
    imageOverviews: List<ImageOverviewViewState>,
    onImageClicked: (ImageOverviewViewState) -> Unit
) {
    // 자동 슬라이딩 효과를 추가하는 부분
    LaunchedEffect(state.currentPage) {
        delay(3000L) // 3초마다 페이지를 자동으로 이동
        val nextPage = (state.currentPage + 1) % imageOverviews.size
        state.animateScrollToPage(nextPage)
    }

    HorizontalPager(
        state = state,
        modifier = modifier,
        pageSpacing = 8.dp,
        contentPadding = PaddingValues(horizontal = 24.dp),
    ) { page ->
        AutoSlidingImageItem(
            modifier = Modifier.fillMaxWidth(),
            imageOverview = imageOverviews[page],
            onClick = onImageClicked,
            totalPage = imageOverviews.size,
            currentPage = page + 1
        )
    }
}

@Composable
fun AutoSlidingImageItem(
    modifier: Modifier = Modifier,
    imageOverview: ImageOverviewViewState,
    totalPage: Int,
    currentPage: Int,
    onClick: (ImageOverviewViewState) -> Unit = {}
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(12.dp))
            .clickable { onClick(imageOverview) }
    ) {
        Image(
            painter = painterResource(id = imageOverview.imageResId), // 로컬 이미지 사용
            contentDescription = imageOverview.title,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
        )

        Row(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(12.dp)
                .clip(RoundedCornerShape(50))
                .background(Color.Black.copy(alpha = 0.6f))
                .padding(horizontal = 6.dp, vertical = 4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "$currentPage / $totalPage",
                color = Color.White,
                style = MaterialTheme.typography.bodySmall
            )
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