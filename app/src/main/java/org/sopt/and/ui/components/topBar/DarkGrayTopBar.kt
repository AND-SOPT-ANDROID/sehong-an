package org.sopt.and.ui.components.topBar

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.KeyboardArrowLeft
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.and.R
import org.sopt.and.ui.theme.ANDANDROIDTheme
import org.sopt.and.ui.theme.darkGray1

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DarkGrayTopBar(
    modifier: Modifier,
    leftContent: @Composable () -> Unit = {},
    centerContent: @Composable () -> Unit = {},
    rightContent: @Composable () -> Unit = {},
    onLeftIconClicked: () -> Unit = {},
    onCenterIconClicked: () -> Unit = {},
    onRightIconClicked: () -> Unit = {},
) {
    Box(
        modifier = modifier
            .background(darkGray1)  // 배경색 설정
            .padding(end = 15.dp)  // 패딩 설정
    ) {
        TopAppBar(
            modifier = Modifier
                .fillMaxWidth(),
            title = {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // 왼쪽 아이콘
                    Box(
                        modifier = Modifier
                            .clickable { onLeftIconClicked() }
                    ) {
                        leftContent()
                    }
                    // 중앙 아이콘
                    Box(
                        modifier = Modifier
                            .clickable { onCenterIconClicked() }
                    ) {
                        centerContent()
                    }
                    // 오른쪽 아이콘
                    Box(
                        modifier = Modifier
                            .clickable { onRightIconClicked() }
                    ) {
                        rightContent()
                    }
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent),
            /** navigationIcon과 actions는 사용하지 않음 */
            navigationIcon = {},
            actions = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SignInTopBarCustomPreview() {
    ANDANDROIDTheme {
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
}

@Preview(showBackground = true)
@Composable
fun HomeTopBarCustomPreview() {
    ANDANDROIDTheme {
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
}

@Preview(showBackground = true)
@Composable
fun SignUpTopBarCustomPreview() {
    ANDANDROIDTheme {
        DarkGrayTopBar(
            modifier = Modifier,
            rightContent = {
                Icon(
                    imageVector = Icons.Filled.Close,
                    contentDescription = "Search",
                    modifier = Modifier.size(24.dp),  // 아이콘 크기 설정
                    tint = Color.White
                )
            },
            onRightIconClicked = { println("Right icon clicked") }
        )
    }
}
