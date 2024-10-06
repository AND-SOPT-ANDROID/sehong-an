package org.sopt.and.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
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
fun TopBarCustom(
    titleContent: @Composable () -> Unit,
    hasLeftIcon: Boolean = false,
    hasRightIcon: Boolean = false,
    onBackClicked: () -> Unit,
    onCloseClicked: () -> Unit
    ) {
    CenterAlignedTopAppBar(
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = darkGray1,
            titleContentColor = Color.White,
            navigationIconContentColor = Color.White,
            actionIconContentColor = Color.White,
        ),
        title = { titleContent() },
        navigationIcon =  {
            if (hasLeftIcon) {
                IconButton(onClick = onBackClicked) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back"
                    )
                }
            }
        },
        actions = {
            if (hasRightIcon) {
                IconButton(onClick = onCloseClicked) {
                    Icon(
                        imageVector = Icons.Filled.Close,
                        contentDescription = "Close"
                    )
                }
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun TopBarCustomPreview() {
    ANDANDROIDTheme {
        TopBarCustom(
//            titleContent = {
//                Text("회원가입")
//            },
            titleContent = {
                Image(
                    painter = painterResource(id = R.drawable.wavve_icon),
                    contentDescription = "Example Image",
                    modifier = Modifier.height(30.dp)
                )
            },
            hasLeftIcon = true,
            hasRightIcon = true,
            onBackClicked = { println("Back clicked") },
            onCloseClicked = { println("Close clicked") }
        )
    }
}