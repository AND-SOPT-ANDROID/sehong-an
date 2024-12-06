package org.sopt.and.presentation.components.text

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.and.R
import org.sopt.and.presentation.theme.WavveTheme

@Composable
fun LabeledIconText(
    text: String,
    icon: Painter,
    color: Color = WavveTheme.colors.gray_3,
    style: TextStyle = WavveTheme.typography.caption,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.padding(horizontal = 10.dp)
    ) {
        Icon(
            painter = icon,
            contentDescription = "exclamation_mark_icon",
            modifier = Modifier
                .size(16.dp)
                .padding(top = 5.dp),
            tint = WavveTheme.colors.white
        )
        Text(
            text = text,
            color = color,
            style = style,
            modifier = Modifier.padding(start = 5.dp)
        )
    }
}

@Preview
@Composable
fun LabeledIconTextPreview() {
    LabeledIconText(
        text = "로그인, 비밀번호 찾기, 알림에 사용되니 정확한 이메일을 입력해 주세요.",
        icon = painterResource(id = R.drawable.exclamation_mark_icon),
        style = WavveTheme.typography.body1.copy(
            color = WavveTheme.colors.gray_3
        )
    )
}