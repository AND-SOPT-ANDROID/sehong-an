package org.sopt.and.ui.components.textField

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.sopt.and.ui.theme.ANDANDROIDTheme
import org.sopt.and.ui.theme.WavveTheme

@Composable
fun FillMaxWidthTextField(
    value: String,
    placeholder: String,
    modifier: Modifier = Modifier,
    onValueChange: (String) -> Unit,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    isPassword: Boolean = false,
    isValid: Boolean = true,
) {
    var passwordVisible by remember { mutableStateOf(false) }
    TextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = { Text(text = placeholder, color = WavveTheme.colors.gray_3) },
        modifier = modifier
            .padding(4.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(4.dp))
            .border(
                width = 1.dp,
                color = if (!isValid) WavveTheme.colors.error_1 else WavveTheme.colors.gray_2,
                shape = RoundedCornerShape(4.dp)
            ),
        isError = !isValid,
        visualTransformation = if (isPassword && !passwordVisible) PasswordVisualTransformation() else VisualTransformation.None,
        colors = TextFieldDefaults.colors(
            cursorColor = WavveTheme.colors.white,
            errorTextColor = WavveTheme.colors.gray_3,
            errorCursorColor = WavveTheme.colors.white,
            focusedTextColor = WavveTheme.colors.gray_3,
            unfocusedTextColor = WavveTheme.colors.gray_3,
            unfocusedContainerColor = WavveTheme.colors.gray_2,
            focusedContainerColor = WavveTheme.colors.gray_2,
            errorContainerColor = WavveTheme.colors.gray_2,
            focusedPlaceholderColor = WavveTheme.colors.gray_2,
            focusedIndicatorColor = WavveTheme.colors.gray_2,
            unfocusedIndicatorColor = WavveTheme.colors.gray_2
        ),
        textStyle = TextStyle(fontSize = 15.sp),
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        trailingIcon = {
            if (isPassword) {
                Text(
                    text = if (passwordVisible) "HIDE" else "SHOW",
                    color = WavveTheme.colors.white,
                    fontSize = WavveTheme.typography.caption.fontSize,
                    modifier = Modifier
                        .clickable { passwordVisible = !passwordVisible }
                        .padding(horizontal = 16.dp)
                )

            }
        },
        singleLine = true
    )
}


@Preview
@Composable
fun TextFieldCustomPreview() {
    var userIdInput by remember { mutableStateOf("") }
    ANDANDROIDTheme {
        FillMaxWidthTextField(
            value = userIdInput,
            placeholder = "Email",
            onValueChange = { value ->
                userIdInput = value
            },
            modifier = Modifier.padding(8.dp),
            isPassword = true
        )
    }
}