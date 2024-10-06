package org.sopt.and

import android.os.Bundle
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.sopt.and.components.TextFieldCustom
import org.sopt.and.components.TopBarCustom
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
                            hasRightIcon = true,
                            onBackClicked = { println("Back clicked") },
                            onCloseClicked = { println("Close clicked") }
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
    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(darkGray1)
    ){
        Spacer(modifier = Modifier.height(30.dp))
        TextFieldCustom(
            value = InputID,
            placeholder = "아이디 주소 또는 아이디",
            onValueChange = { newEmail ->
                InputID = newEmail
            },
            modifier = Modifier.padding(8.dp)
        )
        TextFieldCustom(
            value = InputPassword,
            placeholder = "비밀번호",
            onValueChange = { newEmail ->
                InputPassword = newEmail
            },
            modifier = Modifier.padding(8.dp),
            isPassword = true,
        )
        Spacer(modifier = Modifier.height(30.dp))
        Button(
            onClick = { /* 로그인 실행 */ },
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
            horizontalArrangement = Arrangement.Center
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
                    .weight(0.8f)
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
                    .clickable { /* 회원가입 */ }
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
        Text(
            text = loginDescription,
            fontSize = 12.sp,
            color = Color.Gray,
            modifier = Modifier
                .padding(horizontal = 16.dp)
        )
    }

}



@Preview(showBackground = true)
@Composable
fun SignInPagePreview() {
    ANDANDROIDTheme {
        SignInPage()
    }
}