package org.sopt.and.navigation

sealed class Screen(val route: String) {
    data object SignIn : Screen(route = "login")
    data object SignUp : Screen(route = "signup")
}