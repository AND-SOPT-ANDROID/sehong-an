package org.sopt.and.presentation.ui.search

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun SearchScreen(
    navController: androidx.navigation.NavHostController,
    modifier: Modifier,
    viewModel: SearchViewModel = hiltViewModel()
) {
    Text("Search")
}