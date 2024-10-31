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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.sopt.and.ui.theme.ANDANDROIDTheme
import org.sopt.and.ui.theme.darkGray2
import org.sopt.and.ui.theme.darkGray3
import org.sopt.and.ui.theme.errorColor

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
        placeholder = { Text(text = placeholder, color = darkGray3) },
        modifier = modifier
            .padding(4.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(4.dp))
            .border(
                width = 1.dp,
                color = if (!isValid) errorColor else darkGray2,
                shape = RoundedCornerShape(4.dp)
            ),
        isError = !isValid,
        visualTransformation = if (isPassword && !passwordVisible) PasswordVisualTransformation() else VisualTransformation.None,
        colors = TextFieldDefaults.colors(
            cursorColor = Color.White,
            errorTextColor = darkGray3,
            errorCursorColor = Color.White,
            focusedTextColor = darkGray3,
            unfocusedTextColor = darkGray3,
            unfocusedContainerColor = darkGray2,
            focusedContainerColor = darkGray2,
            errorContainerColor = darkGray2,
            focusedPlaceholderColor = darkGray2,
            focusedIndicatorColor = darkGray2,
            unfocusedIndicatorColor = darkGray2
        ),
        textStyle = TextStyle(fontSize = 15.sp),
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        trailingIcon = {
            if (isPassword) {
                Text(
                    text = if (passwordVisible) "HIDE" else "SHOW",
                    color = Color.White,
                    fontSize = 13.sp,
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