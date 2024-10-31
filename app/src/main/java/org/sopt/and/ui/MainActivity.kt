package org.sopt.and.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import org.sopt.and.navigation.BottomNavItem
import org.sopt.and.navigation.BottomNavigation
import org.sopt.and.navigation.MainNavigation
import org.sopt.and.topbar.TopBar
import org.sopt.and.ui.theme.ANDANDROIDTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    // ViewModel 인스턴스 생성
    private val viewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ANDANDROIDTheme {
                MainScreen(viewModel)
            }
        }
    }
}

@Composable
fun MainScreen(viewModel: MainViewModel) {
    val navController = rememberNavController()
    val screens = listOf(
        BottomNavItem.Home.route,
        BottomNavItem.Search.route,
        BottomNavItem.My.route,
    )
    val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route
    val showBottomNav = currentRoute in screens

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { TopBar(navController, currentRoute) },
        bottomBar = {
            if (showBottomNav) {
                BottomNavigation(navController = navController)
            }
        }
    ) { innerPadding ->
        // Scaffold의 패딩을 content에 적용
        MainNavigation(
            navController = navController,
            modifier = Modifier.padding(innerPadding),
            userManager = viewModel.userManager
        )
    }
}