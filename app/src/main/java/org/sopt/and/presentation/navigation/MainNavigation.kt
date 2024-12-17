package org.sopt.and.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import org.sopt.and.presentation.AuthViewModel
import org.sopt.and.presentation.ui.home.HomeScreen
import org.sopt.and.presentation.ui.myPage.MyScreen
import org.sopt.and.presentation.ui.search.SearchScreen
import org.sopt.and.presentation.ui.signIn.SignInScreen
import org.sopt.and.presentation.ui.signUp.SignUpScreen

@Composable
fun MainNavigation(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    val authViewModel: AuthViewModel = hiltViewModel()
    val isLoggedIn by authViewModel.isLoggedIn.collectAsState()
    NavHost(
        navController = navController,
        startDestination = if (isLoggedIn) Graph.Main.route else Graph.Auth.route
    ) {
        authGraph(navController, modifier)
        mainGraph(navController, modifier)
    }
}

fun NavGraphBuilder.authGraph(
    navController: NavHostController,
    modifier: Modifier
) {
    navigation(startDestination = Screen.SignIn.route, route = Graph.Auth.route) {
        composable(
            route = Screen.SignIn.route
        ) {
            SignInScreen(
                navController = navController,
                modifier = modifier
            )
        }

        composable(
            route = Screen.SignUp.route
        ) {
            SignUpScreen(
                onNavigateToSignIn = {
                    navController.navigate("login") {
                        popUpTo(navController.graph.startDestinationId) {
                            inclusive = true
                        }
                        launchSingleTop = true
                    }
                },
                modifier = modifier
            )
        }
    }
}

fun NavGraphBuilder.mainGraph(
    navController: NavHostController,
    modifier: Modifier
) {
    navigation(startDestination = BottomNavItem.Home.route, route = Graph.Main.route) {
        composable(
            BottomNavItem.Home.route
        ) {
            HomeScreen(navController, modifier)
        }
        composable(
            route = BottomNavItem.Search.route
        ) {
            SearchScreen(navController, modifier)
        }
        composable(
            route = BottomNavItem.My.route
        ) {
            MyScreen(navController, modifier)
        }
    }
}