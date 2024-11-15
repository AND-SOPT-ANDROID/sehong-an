package org.sopt.and.ui.screen.signUp.composable

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
import org.sopt.and.R
import org.sopt.and.data.SocialLogin
import org.sopt.and.ui.components.icon.SocialLoginIcon
import org.sopt.and.ui.components.text.LabeledIconText
import org.sopt.and.ui.components.text.StrikethroughText
import org.sopt.and.ui.components.text.TitleText
import org.sopt.and.ui.components.textField.FillMaxWidthTextField
import org.sopt.and.ui.screen.signUp.contract.SignUpContract
import org.sopt.and.ui.screen.signUp.viewmodel.SignUpViewModel
import org.sopt.and.ui.theme.WavveTheme

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
                    withStyle(
                        style = SpanStyle(
                            color = WavveTheme.colors.white,
                            fontSize = WavveTheme.typography.h1.fontSize,
                        )
                    ) {
                        append("이메일과 비밀번호")
                    }
                    append("만으로\n")
                    withStyle(
                        style = SpanStyle(
                            color = WavveTheme.colors.white,
                            fontSize = WavveTheme.typography.h1.fontSize,
                        )
                    ) {
                        append(" Wavve를 즐길 수")
                    }
                    append("있어요!")
                }
            )
            Spacer(modifier = Modifier.height(20.dp))
            FillMaxWidthTextField(
                value = viewModel.usernameInput,
                placeholder = "닉네임을 입력해 주세요.",
                onValueChange = viewModel::onUsernameInputChange,
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
                isValid = viewModel.isUsernameValid,
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
                value = viewModel.passwordInput,
                placeholder = "Wavve 비밀번호 설정",
                onValueChange = viewModel::onPasswordInputChange,
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
                isValid = viewModel.isPasswordValid,
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
                value = viewModel.hobbyInput,
                placeholder = "취미를 설정해 주세요.",
                onValueChange = viewModel::onHobbyInputChange,
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
                isValid = viewModel.isHobbyValid,
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
//                Toast.makeText(context, "회원가입 성공", Toast.LENGTH_SHORT).show()
//                onNavigateToSignIn()
                viewModel.processEvent(
                    SignUpContract.Event.SignUpButtonClicked(
                        username = viewModel.usernameInput,
                        password = viewModel.passwordInput,
                        hobby = viewModel.hobbyInput
                    )
                )
            },

            shape = RoundedCornerShape(0.dp),
            enabled = viewModel.isEnabled,
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