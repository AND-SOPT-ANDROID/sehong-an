package org.sopt.and.ui.signIn

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
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
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.sopt.and.R
import org.sopt.and.components.TextFieldCustom
import org.sopt.and.components.TopBarCustom
import org.sopt.and.data.PreferencesManager
import org.sopt.and.data.UserManager
import org.sopt.and.ui.myPage.MyActivity
import org.sopt.and.ui.signUp.SignUpActivity
import org.sopt.and.ui.theme.ANDANDROIDTheme
import org.sopt.and.ui.theme.BlueBtnColor
import org.sopt.and.ui.theme.darkGray1
import org.sopt.and.ui.theme.darkGray3

class SignInActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ANDANDROIDTheme {
                Scaffold(
                    topBar = {
                        TopBarCustom (
                            titleContent = {
                                Image(
                                    painter = painterResource(id = R.drawable.wavve_icon),
                                    contentDescription = "main_logo",
                                    modifier = Modifier.height(30.dp)
                                )
                            },
                            hasLeftIcon = true,
                            hasRightIcon = false,
                            onBackClicked = { println("Back clicked") }
                        )
                    },
                    content = { innerPadding ->
                        Box(modifier = Modifier.padding(innerPadding)) {
                            SignInPage()
                        }
                    }
                )
            }
        }
    }
}

@Composable
fun SignInPage() {
    var InputID by remember { mutableStateOf("") }
    var InputPassword by remember { mutableStateOf("") }
    val context = LocalContext.current
    val loginDescription = context.getString(R.string.login_description)
    // SharedPreferences 사용을 위한 PreferencesManager
    val preferencesManager = PreferencesManager(context)
    val userManager = UserManager(preferencesManager)
    // Dialog 표시 여부
    var showDialog by remember { mutableStateOf(false) }
    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(darkGray1)
    ){
        Spacer(modifier = Modifier.height(30.dp))
        TextFieldCustom(
            value = InputID,
            placeholder = "아이디 주소 또는 아이디",
            onValueChange = { value ->
                InputID = value
            },
            modifier = Modifier.padding(8.dp)
        )
        TextFieldCustom(
            value = InputPassword,
            placeholder = "비밀번호",
            onValueChange = { value ->
                InputPassword = value
            },
            modifier = Modifier.padding(8.dp),
            isPassword = true,
        )
        Spacer(modifier = Modifier.height(30.dp))
        Button(
            onClick = {
                val login = userManager.loginUser(InputID, InputPassword)
                if(login) {
                    Toast.makeText(context, "로그인 성공", Toast.LENGTH_SHORT).show()
                    // 자동 로그인
                    userManager.setLoggedIn(true)
                    // MyActivity 화면 이동
                    val intent = Intent(context, MyActivity::class.java).apply {
                        putExtra("email", InputID)  // "email" 키로 이메일 데이터 추가
                    }
                    context.startActivity(intent)
                    // SignInActivity 종료
                    if (context is SignInActivity) context.finish()
                } else {
                    showDialog = true
                }
            },
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
            modifier = Modifier.fillMaxWidth().padding(16.dp),
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
                        val intent = Intent(context, SignUpActivity::class.java)
                        context.startActivity(intent)
                    }
            )
        }
        Spacer(modifier = Modifier.height(30.dp))
        Row(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
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
        // 다이얼로그를 표시할지 여부
        if (showDialog) {
            AlertDialog(
                onDismissRequest = { showDialog = false },
                title = { Text("로그인 실패") },
                text = { Text("아이디 또는 비밀번호가 올바르지 않습니다.") },
                confirmButton = {
                    TextButton(
                        onClick = { showDialog = false }
                    ) {
                        Text("확인")
                    }
                }
            )
        }
    }

}



@Preview(showBackground = true)
@Composable
fun SignInPagePreview() {
    ANDANDROIDTheme {
        Scaffold(
            topBar = {
                TopBarCustom (
                    titleContent = {
                        Image(
                            painter = painterResource(id = R.drawable.wavve_icon),
                            contentDescription = "main_logo",
                            modifier = Modifier.height(30.dp)
                        )
                    },
                    hasLeftIcon = true,
                    hasRightIcon = false,
                    onBackClicked = { println("Back clicked") }
                )
            },
            content = { innerPadding ->
                Box(modifier = Modifier.padding(innerPadding)) {
                    SignInPage()
                }
            }
        )
    }
}