package org.sopt.and.ui.screen.search.composable

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import org.sopt.and.ui.screen.search.viewmodel.SearchViewModel

@Composable
fun SearchScreen(
    navController: androidx.navigation.NavHostController,
    modifier: Modifier,
    viewModel: SearchViewModel = hiltViewModel()
) {
    Text("Search")
}