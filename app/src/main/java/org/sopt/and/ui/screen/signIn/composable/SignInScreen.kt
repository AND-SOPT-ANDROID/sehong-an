package org.sopt.and.ui.screen.signIn.composable

import androidx.compose.foundation.Image
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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import org.sopt.and.R
import org.sopt.and.ui.components.textField.TextFieldCustom
import org.sopt.and.ui.screen.signIn.viewmodel.SignInViewModel
import org.sopt.and.ui.theme.BlueBtnColor
import org.sopt.and.ui.theme.darkGray1
import org.sopt.and.ui.theme.darkGray3


@Composable
fun SignInScreen(
    navController: androidx.navigation.NavHostController,
    modifier: Modifier,
    viewModel: SignInViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val loginDescription = stringResource(id = R.string.login_description)
    Box(modifier = modifier) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(darkGray1)
        ) {
            Spacer(modifier = Modifier.height(30.dp))
            TextFieldCustom(
                value = viewModel.userIdInput,
                placeholder = "아이디 주소 또는 아이디",
                onValueChange = { value ->
                    viewModel.onUserIdInputChange(value)
                },
                modifier = Modifier.padding(8.dp)
            )
            TextFieldCustom(
                value = viewModel.passwordInput,
                placeholder = "비밀번호",
                onValueChange = { value ->
                    viewModel.onPasswordInputChange(value)
                },
                modifier = Modifier.padding(8.dp),
                isPassword = true,
            )
            Spacer(modifier = Modifier.height(30.dp))
            Button(
                onClick = { viewModel.handleLoginClick(navController, context) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                colors = ButtonDefaults.buttonColors(
                    BlueBtnColor,
                    Color.White,
                )
            ) {
                Text(
                    text = "로그인",
                    fontSize = 18.sp,
                    color = Color.White,
                    modifier = Modifier.padding(8.dp)
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                Text(
                    text = "아이디 찾기",
                    color = darkGray3,
                    fontSize = 14.sp,
                    textAlign = TextAlign.Right,
                    modifier = Modifier
                        .weight(1f)
                        .clickable { /* 아이디 찾기 */ }
                )

                Text(
                    text = "|",
                    color = darkGray3,
                    fontSize = 14.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.weight(0.2f)
                )

                Text(
                    text = "비밀번호 설정",
                    color = darkGray3,
                    fontSize = 14.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .clickable { /* 비밀번호 재설정 */ }
                )
                Text(
                    text = "|",
                    color = darkGray3,
                    fontSize = 14.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.weight(0.2f)
                )

                Text(
                    text = "회원가입",
                    color = darkGray3,
                    fontSize = 14.sp,
                    textAlign = TextAlign.Left,
                    modifier = Modifier
                        .weight(1f)
                        .clickable {
                            navController.navigate("signup")
                        }
                )
            }
            Spacer(modifier = Modifier.height(30.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Spacer(
                    modifier = Modifier
                        .weight(1f)
                        .height(1.dp)
                        .padding(end = 8.dp)
                        .background(Color.DarkGray)
                )
                Text(
                    text = "또는 다른 서비스 계정으로 로그인",
                    fontSize = 14.sp,
                    color = Color.Gray,
                    textAlign = TextAlign.Center
                )
                Spacer(
                    modifier = Modifier
                        .weight(1f)
                        .height(1.dp)
                        .padding(start = 8.dp)
                        .background(Color.DarkGray)
                )
            }
            Spacer(modifier = Modifier.height(35.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.Center,
            ) {
                Image(
                    painter = painterResource(id = R.drawable.kakao_icon),
                    contentDescription = "kakao_icon",
                    modifier = Modifier
                        .size(50.dp)
                        .clip(CircleShape)
                )
                Spacer(modifier = Modifier.width(18.dp))
                Image(
                    painter = painterResource(id = R.drawable.t_icon),
                    contentDescription = "t_world_icon",
                    modifier = Modifier
                        .size(50.dp)
                        .clip(CircleShape)
                )
                Spacer(modifier = Modifier.width(18.dp))
                Image(
                    painter = painterResource(id = R.drawable.naver_icon),
                    contentDescription = "naver_icon",
                    modifier = Modifier
                        .size(50.dp)
                        .clip(CircleShape)
                )
                Spacer(modifier = Modifier.width(18.dp))
                Image(
                    painter = painterResource(id = R.drawable.facebook_icon),
                    contentDescription = "facebook_icon",
                    modifier = Modifier
                        .size(50.dp)
                        .clip(CircleShape)
                )
                Spacer(modifier = Modifier.width(18.dp))
                Image(
                    painter = painterResource(id = R.drawable.apple_icon),
                    contentDescription = "apple_icon",
                    modifier = Modifier
                        .size(50.dp)
                        .clip(CircleShape)
                        .background(Color.White)
                )
            }
            Spacer(modifier = Modifier.height(20.dp))
            Row(
                modifier = Modifier.padding(horizontal = 10.dp)
            ) {
                Text(
                    text = "·",
                    color = Color.Gray,
                    fontSize = 12.sp,
                )
                Text(
                    text = loginDescription,
                    color = Color.Gray,
                    fontSize = 12.sp,
                    modifier = Modifier.padding(start = 5.dp)
                )
            }
            /** 다이얼로그를 표시할지 여부 */
            if (viewModel.showDialog) {
                AlertDialog(
                    onDismissRequest = { viewModel.dismissDialog() },
                    title = { Text("로그인 실패") },
                    text = { Text("아이디 또는 비밀번호가 올바르지 않습니다.") },
                    confirmButton = {
                        TextButton(
                            onClick = { viewModel.dismissDialog() }
                        ) {
                            Text("확인")
                        }
                    }
                )
            }
        }
    }
}

