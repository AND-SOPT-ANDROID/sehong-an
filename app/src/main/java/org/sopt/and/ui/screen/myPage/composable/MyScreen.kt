package org.sopt.and.ui.screen.myPage.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.sopt.and.R
import org.sopt.and.ui.components.container.ProfileContainer
import org.sopt.and.ui.components.text.ColumnIconText
import org.sopt.and.ui.screen.myPage.viewmodel.MyScreenViewModel
import org.sopt.and.ui.theme.WavveTheme

@Composable
fun MyScreen(
    navController: androidx.navigation.NavHostController,
    modifier: Modifier,
    viewModel: MyScreenViewModel = hiltViewModel()
) {
    val profileName by viewModel.profileName.collectAsStateWithLifecycle()

    /** 스크롤이 가능하도록 scrollState 설정 */
    val scrollState = rememberScrollState()
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(WavveTheme.colors.gray_5)
            .verticalScroll(scrollState)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(WavveTheme.colors.gray_1)
                .padding(top = 15.dp, start = 15.dp, end = 15.dp, bottom = 30.dp),
        ) {
            ProfileContainer(
                profileName,
                onLogoutClick = {
                    viewModel.logout()
                    navController.navigate("login")
                }
            )
        }
        Text(
            text = "첫 결제 시 첫 달 100원!",
            color = WavveTheme.colors.gray_3,
            modifier = Modifier
                .background(WavveTheme.colors.gray_1)
                .fillMaxWidth()
                .padding(horizontal = 15.dp)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(WavveTheme.colors.gray_1)
                .padding(horizontal = 15.dp)
        ) {
            Text(
                text = "구매하기",
                color = WavveTheme.colors.white,
            )
            Icon(
                imageVector = Icons.AutoMirrored.Outlined.KeyboardArrowRight,
                contentDescription = "구매하기 페이지로 이동",
                tint = WavveTheme.colors.white,
                modifier = Modifier.padding(bottom = 24.dp)
            )
        }
        Spacer(modifier = Modifier.height(1.dp))
        Text(
            text = "현재 보유하신 이용권이 없습니다.",
            color = WavveTheme.colors.gray_3,
            modifier = Modifier
                .background(WavveTheme.colors.gray_1)
                .fillMaxWidth()
                .padding(top = 15.dp, start = 15.dp, end = 15.dp)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(WavveTheme.colors.gray_1)
                .padding(horizontal = 15.dp)
        ) {
            Text(
                text = "구매하기",
                color = WavveTheme.colors.white,
            )
            Icon(
                imageVector = Icons.AutoMirrored.Outlined.KeyboardArrowRight,
                contentDescription = "구매하기 페이지로 이동",
                tint = WavveTheme.colors.white,
                modifier = Modifier.padding(bottom = 24.dp)
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = "전체 시청내역",
            color = WavveTheme.colors.white,
            modifier = Modifier
                .padding(horizontal = 20.dp),
            fontWeight = FontWeight.Bold,
            style = WavveTheme.typography.body2
        )
        ColumnIconText(
            text = "시청내역이 없어요.",
            iconResId = R.drawable.exclamation_mark_icon,
            iconContentDescription = "exclamation_mark_icon"
        )
        Text(
            text = "관심 프로그램",
            color = WavveTheme.colors.white,
            modifier = Modifier
                .padding(horizontal = 20.dp),
            fontWeight = FontWeight.Bold,
            style = WavveTheme.typography.body1
        )
//        Spacer(modifier = Modifier.height(20.dp))
        ColumnIconText(
            text = "관심 프로그램이 없어요.",
            iconResId = R.drawable.exclamation_mark_icon,
            iconContentDescription = "exclamation_mark_icon"
        )
    }
}