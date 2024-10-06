package org.sopt.and.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
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

@Composable
fun TextFieldCustom(
    value: String,
    placeholder: String,
    modifier: Modifier = Modifier,
    onValueChange: (String) -> Unit,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    isPassword: Boolean = false
) {
    var passwordVisible by remember { mutableStateOf(false) }
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = {Text(text = placeholder, fontSize = 12.sp, color = darkGray3)},
        modifier = modifier
            .padding(4.dp)
            .fillMaxWidth(),
        visualTransformation = if (isPassword && !passwordVisible) PasswordVisualTransformation() else VisualTransformation.None,
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = Color.Transparent,
            unfocusedBorderColor = Color.Transparent,
            cursorColor = Color.White,
            unfocusedContainerColor = darkGray2,
            focusedContainerColor = darkGray2,
            focusedTextColor = darkGray3,
            unfocusedTextColor = darkGray3,
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
        }
    )
}



@Preview
@Composable
fun TextFieldCustomPreview() {
    var InputID by remember { mutableStateOf("") }
    ANDANDROIDTheme {
        TextFieldCustom(
            value = InputID,
            placeholder = "Email",
            onValueChange = { value ->
                InputID = value
            },
            modifier = Modifier.padding(8.dp),
            isPassword = true
        )
    }
}