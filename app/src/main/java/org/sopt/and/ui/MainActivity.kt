package org.sopt.and.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import org.sopt.and.data.UserManager
import org.sopt.and.navigation.BottomNavItem
import org.sopt.and.navigation.BottomNavigation
import org.sopt.and.navigation.MainNavigation
import org.sopt.and.topbar.TopBar
import org.sopt.and.ui.theme.ANDANDROIDTheme
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var userManager: UserManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ANDANDROIDTheme {
                val navController = rememberNavController()
                val screens = listOf(
                    BottomNavItem.Home.route,
                    BottomNavItem.Search.route,
                    BottomNavItem.My.route,
                )
                val currentRoute =
                    navController.currentBackStackEntryAsState().value?.destination?.route
                val showBottomNav = navController
                    .currentBackStackEntryAsState().value?.destination?.route in screens.map { it }
                Scaffold(
                    modifier = Modifier
                        .fillMaxSize(),
                    topBar = { TopBar(navController, currentRoute) },
                    bottomBar = {
                        AnimatedVisibility(
                            visible = showBottomNav,
                            enter = fadeIn(),
                            exit = fadeOut()
                        ) {
                            BottomNavigation(navController = navController)
                        }
                    }
                ) { innerPadding ->
                    // Scaffold의 패딩을 content에 적용
                    MainNavigation(
                        navController = navController,
                        modifier = Modifier.padding(innerPadding),
                        userManager = userManager
                    )
                }
            }
        }
    }
}