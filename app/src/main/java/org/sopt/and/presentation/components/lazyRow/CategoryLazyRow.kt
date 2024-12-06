package org.sopt.and.presentation.components.lazyRow

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.and.presentation.theme.WavveTheme

@Composable
fun CategoryLazyRow(
    categories: List<String>,
    modifier: Modifier = Modifier
) {
    LazyRow(
        modifier = modifier
            .fillMaxWidth()
            .background(WavveTheme.colors.gray_1)
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
        style = WavveTheme.typography.title.copy(
            color = Color.LightGray
        ),
        modifier = Modifier
            .padding(horizontal = 8.dp)
            .clickable {

            }
    )
}

@Preview
@Composable
fun PreviewCategoryLazyRow() {
    val categories = listOf("뉴클래식", "드라마", "예능", "영화", "애니", "해외시리즈", "시사교양", "키즈")
    CategoryLazyRow(categories)
}