package org.sopt.and.navigation

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import org.sopt.and.ui.theme.ANDANDROIDTheme

@Composable
fun BottomNavigation(navController: NavController) {
    val backStackEntry = navController.currentBackStackEntryAsState()
    val items = listOf(
        BottomNavItem.Home,
        BottomNavItem.Search,
        BottomNavItem.My
    )
    NavigationBar {
        items.forEach { item ->
            NavigationBarItem(
                icon = {
                    Icon(
                        item.icon,
                        contentDescription = stringResource(id = item.title),
                    )
                },
                label = { Text(text = stringResource(id = item.title)) },
                selected = item.route == backStackEntry.value?.destination?.route,
                onClick = {
                    navController.navigate(item.route) {
                        launchSingleTop = true
                    }
                }
            )
        }
    }
}

@Preview
@Composable
fun MyScreenPreview() {
    ANDANDROIDTheme {
        val navController = rememberNavController()
        BottomNavigation(navController = navController)
    }
}