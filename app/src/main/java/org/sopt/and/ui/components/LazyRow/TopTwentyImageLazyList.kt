package org.sopt.and.ui.components.LazyRow

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
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
import org.sopt.and.R
import org.sopt.and.ui.theme.darkGray1

@Composable
fun TopTwentyImageList(
    imageItems: List<Int>,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
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
                TopTwentyImageListItem(imageRes = imageRes, index = index + 1)
            }
        }
    }
}

@Composable
fun TopTwentyImageListItem(imageRes: Int, index: Int) {
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

@Preview
@Composable
fun PreviewTopTwentyImageList() {
    val imageItems = listOf(
        R.drawable.wavve_image1,
        R.drawable.wavve_image1,
        R.drawable.wavve_image1,
        R.drawable.wavve_image1,
        R.drawable.wavve_image1,
    )
    LazyColumn(
        modifier = Modifier
            .background(darkGray1)
    ) {
        item {
            TopTwentyImageList(imageItems)
        }
    }
}