package org.sopt.and.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import org.sopt.and.ui.screen.home.composable.HomeScreen
import org.sopt.and.ui.screen.myPage.composable.MyScreen
import org.sopt.and.ui.screen.search.composable.SearchScreen
import org.sopt.and.ui.screen.signIn.composable.SignInScreen
import org.sopt.and.ui.screen.signUp.composable.SignUpScreen

@Composable
fun MainNavigation(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    NavHost(navController = navController, startDestination = Graph.Auth.route) {
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
                navController = navController,
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