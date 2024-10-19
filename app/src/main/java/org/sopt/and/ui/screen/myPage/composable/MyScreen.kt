package org.sopt.and.ui.screen.myPage.composable

import android.content.Intent
import androidx.activity.ComponentActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.KeyboardArrowRight
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.sopt.and.R
import org.sopt.and.data.PreferencesManager
import org.sopt.and.data.UserManager
import org.sopt.and.ui.screen.signIn.composable.SignInActivity
import org.sopt.and.ui.theme.darkGray1
import org.sopt.and.ui.theme.darkGray3
import org.sopt.and.ui.theme.darkGray5

@Composable
fun MyScreen(navController: androidx.navigation.NavHostController) {
    var profileName by remember { mutableStateOf("프로필1") }
    val context = LocalContext.current
    val preferencesManager = PreferencesManager(context)
    val userManager = UserManager(preferencesManager)
    profileName = userManager.getUserEmail() ?: "프로필1"
    /** 스크롤이 가능하도록 scrollState 설정 */
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(darkGray5)
            .verticalScroll(scrollState)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(darkGray1)
                .padding(top = 15.dp, start = 15.dp, end = 15.dp, bottom = 30.dp),

            verticalAlignment = Alignment.CenterVertically

        ) {
            Image(
                painter = painterResource(id = R.drawable.profile_icon),
                contentDescription = "profile_logo",
                modifier = Modifier.height(60.dp)
            )
            Spacer(modifier = Modifier.width(20.dp))
            Text(
                text = profileName,
                color = Color.White,
            )
            Text(
                text = "님",
                color = Color.White,
            )
            Spacer(modifier = Modifier.weight(1f))
            Icon(
                imageVector = Icons.Outlined.Notifications,
                contentDescription = "알림",
                tint = Color.White,
                modifier = Modifier.padding(bottom = 4.dp)
            )
            Spacer(modifier = Modifier.width(20.dp))
            Icon(
                imageVector = Icons.Outlined.Settings,
                contentDescription = "세팅",
                tint = Color.White,
                modifier = Modifier
                    .padding(bottom = 4.dp)
                    .clickable {
                        userManager.logoutUser()
                        userManager.setLoggedIn(false)
                        val intent = Intent(context, SignInActivity::class.java)
                        context.startActivity(intent)
                        if (context is ComponentActivity) context.finish()
                    }
            )
        }
        Text(
            text = "첫 결제 시 첫 달 100원!",
            color = darkGray3,
            modifier = Modifier
                .background(darkGray1)
                .fillMaxWidth()
                .padding(horizontal = 15.dp)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(darkGray1)
                .padding(horizontal = 15.dp)
        ) {
            Text(
                text = "구매하기",
                color = Color.White,
            )
            Icon(
                imageVector = Icons.AutoMirrored.Outlined.KeyboardArrowRight,
                contentDescription = "구매하기 페이지로 이동",
                tint = Color.White,
                modifier = Modifier.padding(bottom = 24.dp)
            )
        }
        Spacer(modifier = Modifier.height(1.dp))
        Text(
            text = "현재 보유하신 이용권이 없습니다.",
            color = darkGray3,
            modifier = Modifier
                .background(darkGray1)
                .fillMaxWidth()
                .padding(top = 15.dp, start = 15.dp, end = 15.dp)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(darkGray1)
                .padding(horizontal = 15.dp)
        ) {
            Text(
                text = "구매하기",
                color = Color.White,
            )
            Icon(
                imageVector = Icons.AutoMirrored.Outlined.KeyboardArrowRight,
                contentDescription = "구매하기 페이지로 이동",
                tint = Color.White,
                modifier = Modifier.padding(bottom = 24.dp)
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = "전체 시청내역",
            color = Color.White,
            modifier = Modifier
                .padding(horizontal = 20.dp),
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp
        )
        Spacer(modifier = Modifier.height(20.dp))
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.exclamation_mark_icon),
                contentDescription = "exclamation_mark_icon",
                modifier = Modifier.height(60.dp)
            )
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = "시청내역이 없어요.",
                color = darkGray3,
            )
        }
        Text(
            text = "관심 프로그램",
            color = Color.White,
            modifier = Modifier
                .padding(horizontal = 20.dp),
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp
        )
        Spacer(modifier = Modifier.height(20.dp))
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.exclamation_mark_icon),
                contentDescription = "exclamation_mark_icon",
                modifier = Modifier.height(60.dp)
            )
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = "관심 프로그램이 없어요.",
                color = darkGray3,
            )
        }
    }
}