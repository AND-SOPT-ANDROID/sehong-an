package org.sopt.and.ui.screen.home.composable

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import androidx.navigation.compose.rememberNavController
import org.sopt.and.R
import org.sopt.and.ui.screen.home.viewmodel.HomeViewModel
import org.sopt.and.ui.theme.darkGray1

@Composable
fun HomeScreen(
    navController: androidx.navigation.NavHostController,
    modifier: Modifier,
    viewModel: HomeViewModel = hiltViewModel()
) {
//    Column(
//        modifier = modifier
//            .background(darkGray1)
//    ) {
//        HorizontalCategoryList()
//        ImageLazyList()
//    }
    LazyColumn(
        modifier = modifier
            .background(darkGray1)
    ) {
        item {
            HorizontalCategoryList()
        }
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
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
            ImageLazyList()
        }
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "실시간 인기 콘텐츠",
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
            ImageLazyList()
        }
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "오직 웨이브에서",
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
            ImageLazyList()
        }
    }
}


@Composable
fun HorizontalCategoryList() {
    val categories = listOf("뉴클래식", "드라마", "예능", "영화", "애니", "해외시리즈", "시사교양", "키즈")

    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
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
fun ImageLazyList() {
    val imageItems = listOf(
        R.drawable.wavve_image1,
        R.drawable.wavve_image1,
        R.drawable.wavve_image1,
        R.drawable.wavve_image1,
        R.drawable.wavve_image1
    )

    LazyRow(
        modifier = Modifier
            .fillMaxSize()
    ) {
        items(imageItems) { imageRes ->
            ImageItem(imageRes = imageRes)
        }
    }
}

@Composable
fun ImageItem(imageRes: Int) {
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