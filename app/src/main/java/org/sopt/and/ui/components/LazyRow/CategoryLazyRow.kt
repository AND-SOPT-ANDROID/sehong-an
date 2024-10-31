package org.sopt.and.ui.components.LazyRow

import android.util.Log
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.sopt.and.ui.theme.darkGray1

@Composable
fun CategoryLazyRow(
    categories: List<String>,
    modifier: Modifier = Modifier
) {
    LazyRow(
        modifier = modifier
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
                Log.d("CategoryLazyRow", "$category clicked")
            }
    )
}