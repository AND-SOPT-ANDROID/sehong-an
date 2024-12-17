package org.sopt.and.presentation.ui.signUp

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.sopt.and.R
import org.sopt.and.data.SocialLogin
import org.sopt.and.presentation.components.icon.SocialLoginIcon
import org.sopt.and.presentation.components.text.LabeledIconText
import org.sopt.and.presentation.components.text.StrikethroughText
import org.sopt.and.presentation.components.text.TitleText
import org.sopt.and.presentation.components.textField.FillMaxWidthTextField
import org.sopt.and.presentation.theme.WavveTheme

@Composable
fun SignUpScreen(
    onNavigateToSignIn: () -> Unit,
    modifier: Modifier,
    viewModel: SignUpViewModel = hiltViewModel()
) {
    val icons = SocialLogin.entries
    val context = LocalContext.current
    val loginDescription = stringResource(id = R.string.login_description)
    val focusRequesterEmail = remember { FocusRequester() }
    val focusRequesterPassword = remember { FocusRequester() }
    val focusRequesterHobby = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val effects = viewModel.effect.collectAsState(initial = null).value

    // 효과 처리
    LaunchedEffect(effects) {
        when (effects) {
            is SignUpContract.Effect.NavigateToSignIn -> {
                onNavigateToSignIn()
            }

            is SignUpContract.Effect.ShowSuccessMessage -> {
                Toast.makeText(context, effects.message, Toast.LENGTH_SHORT).show()
                onNavigateToSignIn()
            }

            is SignUpContract.Effect.ShowErrorMessage -> {
                Toast.makeText(context, effects.message, Toast.LENGTH_SHORT).show()
            }

            else -> {}
        }
    }

    val scrollState = rememberScrollState()
    Box(
        modifier = modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(WavveTheme.colors.gray_1)
                .verticalScroll(scrollState)
        ) {
            Spacer(modifier = Modifier.height(30.dp))
            TitleText(
                text = buildAnnotatedString {
                    val commonStyle = SpanStyle(
                        color = WavveTheme.colors.white,
                        fontSize = WavveTheme.typography.h1.fontSize
                    )

                    withStyle(style = commonStyle) {
                        append("이메일과 비밀번호만으로\n")
                        append("Wavve를 즐길 수 ")
                    }
                    append("있어요!")
                }
            )
            Spacer(modifier = Modifier.height(20.dp))
            FillMaxWidthTextField(
                value = uiState.userId,
                placeholder = stringResource(id = R.string.sign_up_placeholder_username),
                onValueChange = viewModel::updateId,
                modifier = Modifier
                    .padding(horizontal = 10.dp)
                    .onFocusChanged { focusState ->
                        viewModel.onEmailFocusChange(focusState.isFocused)
                    }
                    .focusRequester(focusRequesterEmail),
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(
                    onNext = { focusRequesterPassword.requestFocus() }
                ),
                isValid = uiState.isUserIdValid,
            )
            Spacer(modifier = Modifier.height(5.dp))
            LabeledIconText(
                text = viewModel.signUpEmailDescription,
                icon = painterResource(id = R.drawable.exclamation_mark_icon),
                style = WavveTheme.typography.caption.copy(
                    color = WavveTheme.colors.gray_3
                )
            )
            FillMaxWidthTextField(
                value = uiState.password,
                placeholder = stringResource(id = R.string.sign_up_placeholder_password),
                onValueChange = viewModel::updatePassword,
                modifier = Modifier
                    .padding(8.dp)
                    .onFocusChanged { focusState ->
                        viewModel.onPasswordFocusChange(focusState.isFocused)
                    }
                    .focusRequester(focusRequesterPassword),
                isPassword = true,
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = { focusManager.clearFocus() }
                ),
                isValid = uiState.isPasswordValid,
            )
            LabeledIconText(
                text = viewModel.signUpPasswordDescription,
                icon = painterResource(id = R.drawable.exclamation_mark_icon),
                style = WavveTheme.typography.caption.copy(
                    color = WavveTheme.colors.gray_3
                )
            )
            Spacer(modifier = Modifier.height(5.dp))
            FillMaxWidthTextField(
                value = uiState.hobby,
                placeholder = stringResource(id = R.string.sign_up_placeholder_hobby),
                onValueChange = viewModel::updateHobby,
                modifier = Modifier
                    .padding(horizontal = 10.dp)
                    .onFocusChanged { focusState ->
                        viewModel.onHobbyFocusChange(focusState.isFocused)
                    }
                    .focusRequester(focusRequesterHobby),
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = { focusManager.clearFocus() }
                ),
                isValid = uiState.isHobbyValid,
            )
            Spacer(modifier = Modifier.height(5.dp))
            LabeledIconText(
                text = stringResource(id = R.string.sign_up_hobby),
                icon = painterResource(id = R.drawable.exclamation_mark_icon),
                style = WavveTheme.typography.caption.copy(
                    color = WavveTheme.colors.gray_3
                )
            )
            Spacer(modifier = Modifier.height(30.dp))
            StrikethroughText(
                text = stringResource(id = R.string.other_service_login)
            )
            Spacer(modifier = Modifier.height(35.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.Center,
            ) {
                icons.forEachIndexed { index, socialLogin ->
                    SocialLoginIcon(
                        iconRes = socialLogin.iconResId,
                        description = socialLogin.description,
                        modifier = Modifier
                            .size(50.dp)
                            .clip(CircleShape)
                            .then(
                                if (socialLogin.description == "apple_icon") Modifier.background(
                                    WavveTheme.colors.white
                                ) else Modifier
                            )
                    )
                    if (index < icons.size - 1) {
                        Spacer(modifier = Modifier.width(18.dp))
                    }
                }
            }
            Spacer(modifier = Modifier.height(50.dp))
            Row(
                modifier = Modifier.padding(horizontal = 10.dp)
            ) {
                Text(
                    text = "·",
                    style = WavveTheme.typography.caption.copy(
                        color = Color.Gray
                    )
                )
                Text(
                    text = loginDescription,
                    style = WavveTheme.typography.caption.copy(
                        color = Color.Gray
                    ),
                    modifier = Modifier.padding(start = 5.dp)
                )
            }
            Spacer(modifier = Modifier.height(100.dp))
        }
        Button(
            onClick = {
                viewModel.sendEvent(
                    SignUpContract.Event.SignUpButtonClicked(
                        userId = uiState.userId,
                        password = uiState.password,
                        hobby = uiState.hobby
                    )
                )
            },

            shape = RoundedCornerShape(0.dp),
            enabled = uiState.isSignUpEnabled,
            colors = ButtonDefaults.buttonColors(
                containerColor = WavveTheme.colors.main_blue,
                disabledContainerColor = WavveTheme.colors.gray_5,
            ),
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .height(50.dp)
        ) {
            Text(
                text = stringResource(id = R.string.sign_up_button),
                style = WavveTheme.typography.body1.copy(
                    color = WavveTheme.colors.white
                )
            )
        }
    }
}