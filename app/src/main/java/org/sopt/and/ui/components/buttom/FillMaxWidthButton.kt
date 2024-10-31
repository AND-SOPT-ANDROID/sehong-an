package org.sopt.and.ui.components.buttom

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.sopt.and.ui.theme.BlueBtnColor

@Composable
fun FillMaxWidthButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    buttonColors: ButtonColors = ButtonDefaults.buttonColors(
        containerColor = BlueBtnColor,
        contentColor = Color.White
    ),
    fontSize: TextUnit = 18.sp
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
            fontSize = fontSize,
            color = Color.White,
            modifier = Modifier.padding(8.dp)
        )
    }
}
