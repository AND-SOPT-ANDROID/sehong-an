package org.sopt.and.ui.components.buttom

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.and.ui.theme.WavveTheme

@Composable
fun FillMaxWidthButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    buttonColors: ButtonColors = ButtonDefaults.buttonColors(
        containerColor = WavveTheme.colors.main_blue,
        contentColor = WavveTheme.colors.white
    ),
    style: TextStyle = WavveTheme.typography.title,
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
        colors = buttonColors
    ) {
        Text(
            text = text,
            style = style,
            color = WavveTheme.colors.white,
            modifier = Modifier.padding(8.dp)
        )
    }
}

@Preview
@Composable
fun FillMaxWidthButtonPreview() {
    FillMaxWidthButton(
        text = "로그인",
        onClick = { }
    )
}

