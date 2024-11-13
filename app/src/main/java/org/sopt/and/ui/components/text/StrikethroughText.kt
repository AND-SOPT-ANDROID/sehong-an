package org.sopt.and.ui.components.text

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.and.ui.theme.WavveTheme

@Composable
fun StrikethroughText(
    text: String,
    style: TextStyle = WavveTheme.typography.body1,
    color: Color = Color.Gray,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Spacer(
            modifier = Modifier
                .weight(1f)
                .height(1.dp)
                .padding(end = 8.dp)
                .background(Color.DarkGray)
        )
        Text(
            text = text,
            style = style,
            color = color,
            textAlign = TextAlign.Center
        )
        Spacer(
            modifier = Modifier
                .weight(1f)
                .height(1.dp)
                .padding(start = 8.dp)
                .background(Color.DarkGray)
        )
    }
}


@Preview
@Composable
fun StrikethroughTextPreview() {
    StrikethroughText(
        text = "또는 다른 서비스 계정으로 로그인"
    )
}