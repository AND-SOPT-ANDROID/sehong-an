package org.sopt.and.ui.components.icon

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import org.sopt.and.R


@Composable
fun SocialLoginIcon(
    iconRes: Int,
    description: String,
    modifier: Modifier = Modifier
) {
    Image(
        painter = painterResource(id = iconRes),
        contentDescription = description,
        modifier = modifier
    )
}

@Preview
@Composable
fun SocialLoginIconPreview() {
    SocialLoginIcon(
        iconRes = R.drawable.kakao_icon,
        description = "kakao Icon"
    )
}