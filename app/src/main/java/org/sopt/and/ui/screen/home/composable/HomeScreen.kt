package org.sopt.and.ui.screen.home.composable

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import org.sopt.and.ui.screen.home.viewmodel.HomeViewModel

@Composable
fun HomeScreen(
    navController: androidx.navigation.NavHostController,
    modifier: Modifier,
    viewModel: HomeViewModel = hiltViewModel()
) {
    Box(modifier = modifier) {
        Text("home")

    }
}

@Preview(showBackground = true)
@Composable
fun PreviewHomeScreen() {
    // 네비게이션 컨트롤러를 미리 보기용으로 생성합니다.
    val navController = rememberNavController()

    // HomeScreen을 호출하며 필요한 파라미터를 제공합니다.
    Box(modifier = Modifier.fillMaxSize()) {
        HomeScreen(navController = navController, modifier = Modifier.fillMaxSize())
    }
}