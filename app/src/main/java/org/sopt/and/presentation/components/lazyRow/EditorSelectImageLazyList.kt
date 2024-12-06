package org.sopt.and.presentation.components.lazyRow

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.and.R
import org.sopt.and.presentation.theme.WavveTheme

@Composable
fun EditorSelectImageLazyList(
    imageItems: List<Int>,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 16.dp, bottom = 5.dp, top = 20.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = stringResource(id = R.string.wavve_editor_suggestion),
            style = WavveTheme.typography.title.copy(
                color = WavveTheme.colors.white
            ),
            fontWeight = FontWeight.Bold
        )
        Icon(
            imageVector = Icons.Default.ArrowForward,
            contentDescription = null,
            tint = WavveTheme.colors.white
        )
    }
    LazyRow(
        modifier = Modifier
            .fillMaxSize()
    ) {
        items(imageItems) { imageRes ->
            EditorSelectImageLazyListItem(imageRes = imageRes)
        }
    }
}

@Composable
fun EditorSelectImageLazyListItem(imageRes: Int) {
    Image(
        painter = painterResource(id = imageRes),
        contentDescription = null,
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .padding(8.dp)
            .clip(RoundedCornerShape(8.dp)),
        contentScale = ContentScale.Crop
    )
}

@Preview
@Composable
fun PreviewEditorSelectImageLazyList() {
    val imageItems = listOf(
        R.drawable.wavve_image1,
        R.drawable.wavve_image1,
        R.drawable.wavve_image1,
        R.drawable.wavve_image1,
        R.drawable.wavve_image1,
    )
    LazyColumn(
        modifier = Modifier
            .background(WavveTheme.colors.gray_1)
    ) {
        item {
            EditorSelectImageLazyList(imageItems)
        }
    }
}