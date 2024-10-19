package org.sopt.and.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.sopt.and.data.PreferencesManager
import org.sopt.and.data.UserManager
import org.sopt.and.ui.screen.myPage.composable.MyScreen
import org.sopt.and.ui.screen.signIn.composable.SignInScreen
import org.sopt.and.ui.screen.signUp.composable.SignUpScreen

@Composable
fun WavveNavigation() {
    val navController = rememberNavController()
    val startDestination = remember { mutableStateOf("signIn") }
    val context = LocalContext.current
    val preferencesManager = PreferencesManager(context)
    val userManager = UserManager(preferencesManager)
    /** 로그인 상태에 따라 시작 화면을 결정 */
    LaunchedEffect(key1 = true) {
        startDestination.value = if (userManager.isLoggedIn()) "myPage" else "signIn"
    }
    NavHost(navController = navController, startDestination = startDestination.value) {
        composable("home") { }
        composable("search") { }
        composable("myPage") { MyScreen(navController) }
        composable("signIn") { SignInScreen(navController) }
        composable("signUp") { SignUpScreen(navController) }
    }
}