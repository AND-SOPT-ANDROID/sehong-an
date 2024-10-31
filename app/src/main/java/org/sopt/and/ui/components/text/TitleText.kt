package org.sopt.and.ui.components.text

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.sopt.and.ui.theme.darkGray3

@Composable
fun TitleText(
    text: AnnotatedString,
    lineHeight: TextUnit = 40.sp,
    fontSize: TextUnit = 22.sp,
    color: Color = darkGray3,  // 기본 색상을 검은색으로 설정
    modifier: Modifier = Modifier.padding(start = 30.dp)
) {
    Text(
        text = text,
        lineHeight = lineHeight,
        fontSize = fontSize,
        color = color,
        modifier = modifier,
    )
}