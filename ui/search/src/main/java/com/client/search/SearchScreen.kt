package com.client.search

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.client.search.component.SearchBarView
import com.client.ui.DevicePreviews
import com.client.ui.util.DummyData

@Composable
fun SearchScreenRoute(
    navController: NavHostController,
    viewModel: SearchViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    SearchScreen(
        searchUiState = uiState,
        navController = navController,
        onQueryChanged = viewModel::search,
        onClear = viewModel::onClear
    )
}

@Composable
internal fun SearchScreen(
    searchUiState: SearchUiState,
    navController: NavHostController,
    onQueryChanged: (String) -> Unit,
    onClear: () -> Unit
) {
    SearchBarView(
        navController = navController,
        searchUiState = searchUiState,
        onQueryChanged = onQueryChanged,
        onClear = onClear
    )
}

@DevicePreviews
@Composable
fun SearchScreenPreview() {
    val rates = DummyData.rates()
    SearchScreen(
        searchUiState = SearchUiState.Success(rates = rates),
        navController = rememberNavController(),
        onQueryChanged = {}
    ) {}
}

@Preview(showBackground = true)
@Composable
fun SearchScreenEmptyStatePreview() {
    SearchScreen(
        searchUiState = SearchUiState.Empty,
        navController = rememberNavController(),
        onQueryChanged = {}
    ) {}
}
