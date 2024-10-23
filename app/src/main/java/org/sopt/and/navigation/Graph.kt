package org.sopt.and.navigation

sealed class Graph(val route: String) {
    data object Auth : Graph(route = "auth")
    data object Main : Graph(route = "main")
}