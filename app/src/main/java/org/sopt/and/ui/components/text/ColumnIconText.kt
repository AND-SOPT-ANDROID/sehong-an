package org.sopt.and.ui.components.text

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.and.R
import org.sopt.and.ui.theme.WavveTheme

@Composable
fun ColumnIconText(
    text: String,
    iconResId: Int,
    iconContentDescription: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .height(200.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = iconResId),
            contentDescription = iconContentDescription,
            modifier = Modifier.height(60.dp)
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = text,
            color = WavveTheme.colors.gray_3,
        )
    }
}

@Preview
@Composable
fun PreviewColumnIconText() {
    ColumnIconText(
        text = stringResource(id = R.string.my_screen_no_history),
        iconResId = R.drawable.exclamation_mark_icon,
        iconContentDescription = "exclamation_mark_icon"
    )
}