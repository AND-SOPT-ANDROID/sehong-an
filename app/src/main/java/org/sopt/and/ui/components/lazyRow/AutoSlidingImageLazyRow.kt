package org.sopt.and.ui.components.lazyRow

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import org.sopt.and.R
import org.sopt.and.ui.screen.home.viewmodel.ImageOverviewViewState
import org.sopt.and.ui.theme.WavveTheme

@Composable
fun AutoSlidingImagePager(
    state: PagerState,
    imageOverviews: List<ImageOverviewViewState>,
    onImageClicked: (ImageOverviewViewState) -> Unit = {},
    modifier: Modifier = Modifier,
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
                text = stringResource(R.string.page_indicator, currentPage, totalPage),
                color = WavveTheme.colors.white,
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}

@Preview
@Composable
fun PreviewAutoSlidingImagePager() {
    val imageItems = listOf(
        R.drawable.wavve_image1,
        R.drawable.wavve_image1,
        R.drawable.wavve_image1,
        R.drawable.wavve_image1,
        R.drawable.wavve_image1,
    )
    val pagerState = rememberPagerState(
        initialPage = 0,
        initialPageOffsetFraction = 0f,
        pageCount = { imageItems.size }
    )
    LazyColumn(
        modifier = Modifier
            .background(WavveTheme.colors.gray_1)
    ) {
        item {
            AutoSlidingImagePager(
                state = pagerState,
                imageOverviews = imageItems.map { ImageOverviewViewState(it, "") },
            )
        }
    }
}