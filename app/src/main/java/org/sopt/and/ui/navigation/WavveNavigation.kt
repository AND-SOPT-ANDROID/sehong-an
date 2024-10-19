package org.sopt.and.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.sopt.and.ui.screen.myPage.composable.MyScreen
import org.sopt.and.ui.screen.signIn.composable.SignInScreen
import org.sopt.and.ui.screen.signUp.composable.SignUpScreen

@Composable
fun WavveNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "signIn") {
        composable("home") { }
        composable("search") { }
        composable("myPage") { MyScreen(navController) }
        composable("signIn") { SignInScreen(navController) }
        composable("signUp") { SignUpScreen(navController) }
    }
}