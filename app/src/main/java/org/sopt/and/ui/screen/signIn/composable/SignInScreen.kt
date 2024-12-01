package org.sopt.and.ui.screen.signIn.composable

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import org.sopt.and.R
import org.sopt.and.data.SocialLogin
import org.sopt.and.ui.components.buttom.FillMaxWidthButton
import org.sopt.and.ui.components.icon.SocialLoginIcon
import org.sopt.and.ui.components.text.StrikethroughText
import org.sopt.and.ui.components.textField.FillMaxWidthTextField
import org.sopt.and.ui.screen.signIn.contract.SignInContract
import org.sopt.and.ui.screen.signIn.viewmodel.SignInViewModel
import org.sopt.and.ui.theme.WavveTheme


@Composable
fun SignInScreen(
    navController: androidx.navigation.NavHostController,
    modifier: Modifier,
    viewModel: SignInViewModel = hiltViewModel(),
) {
    val icons = SocialLogin.entries
    val context = LocalContext.current
    val loginDescription = stringResource(id = R.string.login_description)
    val effects = viewModel.effect.collectAsState(initial = null).value
    LaunchedEffect(effects) {
        when (effects) {
            is SignInContract.Effect.NavigateToMyScreen -> {
                navController.navigate("my")
            }

            is SignInContract.Effect.ShowSuccessMessage -> {
                Toast.makeText(context, effects.message, Toast.LENGTH_SHORT).show()
                navController.navigate("my")
            }

            is SignInContract.Effect.ShowErrorMessage -> {
                Toast.makeText(context, effects.message, Toast.LENGTH_SHORT).show()
            }

            else -> {}
        }
    }

    Box(modifier = modifier) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(WavveTheme.colors.gray_1)
        ) {
            Spacer(modifier = Modifier.height(30.dp))
            FillMaxWidthTextField(
                value = viewModel.userIdInput,
                placeholder = stringResource(id = R.string.sign_in_id_text_field),
                onValueChange = viewModel::onUserIdInputChange,
                modifier = Modifier.padding(8.dp)
            )
            FillMaxWidthTextField(
                value = viewModel.passwordInput,
                placeholder = stringResource(id = R.string.sign_in_password_text_field),
                onValueChange = viewModel::onPasswordInputChange,
                modifier = Modifier.padding(8.dp),
                isPassword = true,
            )
            Spacer(modifier = Modifier.height(30.dp))
            FillMaxWidthButton(
                text = stringResource(id = R.string.sign_in_button),
                style = WavveTheme.typography.body1,
                onClick = {
                    viewModel.sendEvent(
                        SignInContract.Event.SignInButtonClicked(
                            username = viewModel.userIdInput,
                            password = viewModel.passwordInput,
                        )
                    )
                }
            )
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                ActionText(
                    text = stringResource(id = R.string.sign_in_search_id),
                    onClick = { /* 아이디 찾기 */ },
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.Right
                )
                DividerText(
                    modifier = Modifier.weight(0.2f),
                )
                ActionText(
                    text = stringResource(id = R.string.sign_in_reset_password),
                    onClick = { /* 비밀번호 재설정 */ }
                )
                DividerText(
                    modifier = Modifier.weight(0.2f),
                )
                ActionText(
                    text = stringResource(id = R.string.sign_in_go_sign_up),
                    onClick = { navController.navigate("signup") },
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.Left
                )
            }
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
            Spacer(modifier = Modifier.height(20.dp))
            Row(
                modifier = Modifier.padding(horizontal = 10.dp)
            ) {
                Text(
                    text = loginDescription,
                    style = WavveTheme.typography.body2.copy(
                        color = Color.Gray
                    ),
                )
            }
            /** 다이얼로그를 표시할지 여부 */
            if (viewModel.showDialog) {
                AlertDialog(
                    onDismissRequest = { viewModel.dismissDialog() },
                    title = { Text(stringResource(id = R.string.login_fail)) },
                    text = { Text(stringResource(id = R.string.login_fail_message)) },
                    confirmButton = {
                        TextButton(
                            onClick = { viewModel.dismissDialog() }
                        ) {
                            Text(stringResource(id = R.string.confirm))
                        }
                    }
                )
            }
        }
    }
}

@Composable
private fun ActionText(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    textAlign: TextAlign = TextAlign.Center
) {
    Text(
        text = text,
        style = WavveTheme.typography.body2.copy(color = WavveTheme.colors.gray_3),
        textAlign = textAlign,
        modifier = modifier
            .clickable(onClick = onClick)
    )
}

@Composable
private fun DividerText(
    modifier: Modifier = Modifier,
) {
    Text(
        text = "|",
        style = WavveTheme.typography.body2.copy(color = WavveTheme.colors.gray_3),
        textAlign = TextAlign.Center,
        modifier = modifier
    )
}