package org.sopt.and.topbar

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.KeyboardArrowLeft
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import org.sopt.and.R
import org.sopt.and.navigation.BottomNavItem
import org.sopt.and.navigation.Screen
import org.sopt.and.ui.components.topBar.DarkGrayTopBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(navController: NavHostController, currentRoute: String?) {
    when (currentRoute) {
        BottomNavItem.Home.route -> {
            DarkGrayTopBar(
                modifier = Modifier,
                leftContent = {
                    Image(
                        painter = painterResource(id = R.drawable.wavve_icon),
                        contentDescription = "MainIcon",
                        modifier = Modifier.size(80.dp)
                    )
                },
                rightContent = {
                    Icon(
                        imageVector = Icons.Filled.Search,
                        contentDescription = "Search",
                        modifier = Modifier.size(24.dp),  // 아이콘 크기 설정
                        tint = Color.White
                    )
                },
                onLeftIconClicked = { println("Left icon clicked") },
                onRightIconClicked = { println("Right icon clicked") }
            )
        }

        BottomNavItem.Search.route -> {
            /** SearchPage도 TopBar 없음*/
        }

        BottomNavItem.My.route -> {
            /** 프로필은 TopBar 없음*/
        }

        Screen.SignIn.route -> {
            DarkGrayTopBar(
                modifier = Modifier,
                leftContent = {
                    Icon(
                        imageVector = Icons.Outlined.KeyboardArrowLeft,
                        contentDescription = "Menu",
                        modifier = Modifier
                            .size(30.dp)  // 아이콘 크기 설정
                            .padding(start = 0.dp),
                        tint = Color.White
                    )
                },
                centerContent = {
                    Image(
                        painter = painterResource(id = R.drawable.wavve_icon),
                        contentDescription = "MainIcon",
                        modifier = Modifier.size(80.dp)
                    )
                },
                onLeftIconClicked = { println("Left icon clicked") },
                onCenterIconClicked = { println("Center icon clicked") }
            )
        }

        Screen.SignUp.route -> {
            DarkGrayTopBar(
                modifier = Modifier,
                rightContent = {
                    Icon(
                        imageVector = Icons.Filled.Close,
                        contentDescription = "Close",
                        modifier = Modifier.size(24.dp),  // 아이콘 크기 설정
                        tint = Color.White
                    )
                },
                onRightIconClicked = { println("Right icon clicked") }
            )
        }

        else -> {
            // 기본 상단 바 또는 빈 상단 바 설정
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true, name = "Home TopAppBar Preview")
@Composable
fun PreviewHomeTopAppBar() {
    DarkGrayTopBar(
        modifier = Modifier,
        leftContent = {
            Image(
                painter = painterResource(id = R.drawable.wavve_icon),
                contentDescription = "MainIcon",
                modifier = Modifier.size(80.dp)
            )
        },
        rightContent = {
            Icon(
                imageVector = Icons.Filled.Search,
                contentDescription = "Search",
                modifier = Modifier.size(24.dp),  // 아이콘 크기 설정
                tint = Color.White
            )
        },
        onLeftIconClicked = { println("Left icon clicked") },
        onRightIconClicked = { println("Right icon clicked") }
    )
}

@Preview(showBackground = true, name = "Sign In TopAppBar Preview")
@Composable
fun PreviewSignInTopAppBar() {
    DarkGrayTopBar(
        modifier = Modifier,
        leftContent = {
            Icon(
                imageVector = Icons.Outlined.KeyboardArrowLeft,
                contentDescription = "Menu",
                modifier = Modifier
                    .size(30.dp)  // 아이콘 크기 설정
                    .padding(start = 0.dp),
                tint = Color.White
            )
        },
        centerContent = {
            Image(
                painter = painterResource(id = R.drawable.wavve_icon),
                contentDescription = "MainIcon",
                modifier = Modifier.size(120.dp)
            )
        },
        onLeftIconClicked = { println("Left icon clicked") },
        onCenterIconClicked = { println("Center icon clicked") }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true, name = "Sign Up TopAppBar Preview")
@Composable
fun PreviewSignUpTopAppBar() {
    DarkGrayTopBar(
        modifier = Modifier,
        rightContent = {
            Icon(
                imageVector = Icons.Filled.Close,
                contentDescription = "Close",
                modifier = Modifier.size(24.dp),  // 아이콘 크기 설정
                tint = Color.White
            )
        },
        onRightIconClicked = { println("Right icon clicked") }
    )
}
