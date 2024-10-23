package org.sopt.and.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector
import org.sopt.and.R

sealed class BottomNavItem(
    val title: Int, val icon: ImageVector, val route: String
) {
    data object Home : BottomNavItem(R.string.title_home, Icons.Filled.Home, "home")
    data object Search : BottomNavItem(R.string.title_search, Icons.Filled.Search, "search")
    data object My : BottomNavItem(R.string.title_my, Icons.Filled.Person, "my")
}