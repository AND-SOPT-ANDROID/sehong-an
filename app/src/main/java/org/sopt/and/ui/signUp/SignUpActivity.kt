package org.sopt.and.ui.signUp

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.sopt.and.R
import org.sopt.and.components.TextFieldCustom
import org.sopt.and.components.TopBarCustom
import org.sopt.and.data.PreferencesManager
import org.sopt.and.data.UserManager
import org.sopt.and.ui.theme.ANDANDROIDTheme
import org.sopt.and.ui.theme.BlueBtnColor
import org.sopt.and.ui.theme.darkGray1
import org.sopt.and.ui.theme.darkGray3
import org.sopt.and.ui.theme.darkGray4
import org.sopt.and.ui.theme.errorColor
import org.sopt.and.utils.isValidEmail
import org.sopt.and.utils.isValidPassword


class SignUpActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ANDANDROIDTheme {
                val context = LocalContext.current
                Scaffold(
                    topBar = {
                        TopBarCustom(
                            titleContent = {
                                Text(
                                    text = "회원가입"
                                )
                            },
                            hasLeftIcon = false,
                            hasRightIcon = true,
                            onCloseClicked = {
                                if (context is ComponentActivity) {
                                    context.finish()
                                }
                            }
                        )
                    },
                    content = { innerPadding ->
                        Box(modifier = Modifier.padding(innerPadding)) {
                            SignUpPage()
                        }
                    }
                )
            }
        }
    }
}

@Composable
private fun SignUpPage() {
    val context = LocalContext.current

    /** Email 입력값 */
    var userIdInput by remember { mutableStateOf("") }

    /** Password 입력값 */
    var passwordInput by remember { mutableStateOf("") }

    /** 회원가입 가능 여부 */
    var isEnabled by remember { mutableStateOf(false) }

    /** Email Valid 여부 */
    var isEmailValid by remember { mutableStateOf(true) }

    /** Password Valid 여부 */
    var isPasswordValid by remember { mutableStateOf(true) }

    /** 로그인 설명 R.string.login_description 참고 */
    val loginDescription = stringResource(id = R.string.login_description)

    /** 로그인 설명 R.string.login_description 참고 */
    val signUpEmailDefault = stringResource(id = R.string.sign_up_email_default)

    /** 로그인 설명 R.string.login_description 참고 */
    val signUpEmailError1 = stringResource(id = R.string.sign_up_email_error1)

    /** 로그인 설명 R.string.login_description 참고 */
    val signUpEmailError2 = stringResource(id = R.string.sign_up_email_error2)

    /** 로그인 설명 R.string.login_description 참고 */
    val signUpPasswordDefault = stringResource(id = R.string.sign_up_password_default)

    /** 로그인 설명 R.string.login_description 참고 */
    val signUpPasswordError1 = stringResource(id = R.string.sign_up_password_error1)

    /** 각 TextField 의 포커스를 요청하기 위한 FocusRequester 생성 */
    val focusRequesterEmail = remember { FocusRequester() }
    val focusRequesterPassword = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current

    /** Email 초기 포커스 한번은 무시하기 */
    var hasFocusEmailChanged by remember { mutableStateOf(false) }

    /** Password 초기 포커스 한번은 무시하기 */
    var hasFocusPasswordChanged by remember { mutableStateOf(false) }

    /** Email 의 Description 을 원하는 타이밍에 변경하고 싶기 때문에 remember 사용 */
    var signUpEmailDescription by remember { mutableStateOf(signUpEmailDefault) }

    /** Password 의 Description 을 원하는 타이밍에 변경하고 싶기 때문에 remember 사용 */
    var signUpPasswordDescription by remember { mutableStateOf(signUpPasswordDefault) }

    /** SharedPreferences 사용을 위한 PreferencesManager */
    val preferencesManager = PreferencesManager(context)
    val userManager = UserManager(preferencesManager)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(darkGray1)
    ) {
        Spacer(modifier = Modifier.height(30.dp))
        Text(
            text = buildAnnotatedString {
                withStyle(style = SpanStyle(color = Color.White, fontSize = 22.sp)) {
                    append("이메일과 비밀번호")
                }
                append("만으로\n")
                withStyle(style = SpanStyle(color = Color.White, fontSize = 22.sp)) {
                    append(" Wavve를 즐길 수")
                }
                append("있어요!")
            },
            lineHeight = 40.sp,
            fontSize = 22.sp,
            color = darkGray3,
            modifier = Modifier
                .padding(start = 30.dp)
        )
        Spacer(modifier = Modifier.height(20.dp))
        TextFieldCustom(
            value = userIdInput,
            placeholder = "wavve@example.com",
            onValueChange = { value ->
                userIdInput = value
            },
            modifier = Modifier
                .padding(horizontal = 10.dp)
                .onFocusChanged { focusState ->
                    if (hasFocusEmailChanged) {
                        if (!focusState.isFocused) {
                            isEnabled = if (!(userIdInput.isEmpty() || passwordInput.isEmpty())) {
                                isValidEmail(userIdInput) && isValidPassword(passwordInput)
                            } else {
                                false
                            }
                            isEmailValid = if (userIdInput.isNotEmpty()) {
                                isValidEmail(userIdInput)
                            } else {
                                false
                            }
                            signUpEmailDescription = if (userIdInput.length < 5) {
                                signUpEmailError1
                            } else {
                                if (isEmailValid) signUpEmailDefault
                                else signUpEmailError2
                            }
                        }
                    }
                    hasFocusEmailChanged = true
                }
                .focusRequester(focusRequesterEmail),
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(
                onNext = { focusRequesterPassword.requestFocus() }
            ),
            isValid = isEmailValid,
        )
        Spacer(modifier = Modifier.height(5.dp))
        Row(
            modifier = Modifier.padding(horizontal = 10.dp)
        ) {
            Text(
                text = "ⓘ",
                color = if (isEmailValid) darkGray3 else errorColor,
                fontSize = 13.sp,
            )
            Text(
                text = signUpEmailDescription,
                color = if (isEmailValid) darkGray3 else errorColor,
                fontSize = 13.sp,
                modifier = Modifier.padding(start = 5.dp)
            )
        }

        TextFieldCustom(
            value = passwordInput,
            placeholder = "Wavve 비밀번호 설정",
            onValueChange = { value ->
                passwordInput = value
            },
            modifier = Modifier
                .padding(8.dp)
                .onFocusChanged { focusState ->
                    if (hasFocusPasswordChanged) {
                        if (!focusState.isFocused) {
                            isEnabled = if (!(userIdInput.isEmpty() || passwordInput.isEmpty())) {
                                isValidEmail(userIdInput) && isValidPassword(passwordInput)
                            } else {
                                false
                            }
                            isPasswordValid = if (passwordInput.isNotEmpty()) {
                                isValidPassword(passwordInput)
                            } else {
                                false
                            }
                            signUpPasswordDescription = if (isPasswordValid) signUpPasswordDefault
                            else signUpPasswordError1
                        }
                    }
                    hasFocusPasswordChanged = true
                }
                .focusRequester(focusRequesterPassword),
            isPassword = true,
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = { focusManager.clearFocus() }
            ),
            isValid = isPasswordValid,
        )
        Row(
            modifier = Modifier.padding(horizontal = 10.dp)
        ) {
            Text(
                text = "ⓘ",
                color = if (isPasswordValid) darkGray3 else errorColor,
                fontSize = 13.sp,
            )
            Text(
                text = signUpPasswordDescription,
                color = if (isPasswordValid) darkGray3 else errorColor,
                fontSize = 13.sp,
                modifier = Modifier.padding(start = 5.dp)
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
        Spacer(modifier = Modifier.height(50.dp))
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
        Spacer(modifier = Modifier.weight(1f))
        Button(
            onClick = {
                userManager.registerUser(userIdInput, passwordInput)
                Toast.makeText(context, "회원가입 성공", Toast.LENGTH_SHORT).show()
                if (context is ComponentActivity) context.finish()
            },
            shape = RoundedCornerShape(0.dp),
            enabled = isEnabled,
            colors = ButtonDefaults.buttonColors(
                containerColor = BlueBtnColor,
                disabledContainerColor = darkGray4,
            ),
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
        ) {
            Text(
                text = "Wavve 회원가입",
                color = Color.White,
                fontSize = 15.sp,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SignUpPagePreview() {
    ANDANDROIDTheme {
        val context = LocalContext.current
        Scaffold(
            topBar = {
                TopBarCustom(
                    titleContent = {
                        Text(
                            text = "회원가입"
                        )
                    },
                    hasLeftIcon = false,
                    hasRightIcon = true,
                    onCloseClicked = {
                        if (context is ComponentActivity) {
                            context.finish()
                        }
                    }
                )
            },
            content = { innerPadding ->
                Box(modifier = Modifier.padding(innerPadding)) {
                    SignUpPage()
                }
            }
        )
    }
}