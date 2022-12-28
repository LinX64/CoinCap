package com.client.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.client.detail.navigation.navigateToDetail
import com.client.search.component.InitialView
import com.client.search.component.ItemsContent
import com.client.ui.DevicePreviews
import com.client.ui.ErrorView
import com.client.ui.ProgressBar
import com.client.ui.SearchBar
import com.client.ui.util.DummyData

@Composable
fun SearchRoute(
    navController: NavHostController,
    viewModel: SearchViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    if (uiState is SearchUiState.Loaded) {
        val rates = (uiState as SearchUiState.Loaded).rates
        println(rates)
    }

    SearchScreen(
        searchUiState = uiState,
        navController = navController,
        onQueryChanged = viewModel::search,
        onClear = viewModel::onClear
    )
}

@Composable
fun SearchScreen(
    modifier: Modifier = Modifier,
    searchUiState: SearchUiState,
    navController: NavHostController,
    onQueryChanged: (String) -> Unit,
    onClear: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        SearchBar(
            onQueryChange = { onQueryChanged(it.text) },
            onClear = onClear
        )

        Spacer(modifier = modifier.padding(8.dp))

        when (searchUiState) {
            is SearchUiState.Loading -> ProgressBar()
            is SearchUiState.Success -> ItemsContent(
                rates = searchUiState.rates,
                onRateClicked = { rateId ->
                    navController.navigateToDetail(rateId)
                }
            )
            is SearchUiState.Error -> ErrorView(errorMessage = searchUiState.message)
            else -> Unit
        }

        InitialView()
    }
}

@DevicePreviews
@Composable
fun SearchScreenPreview() {
    val rates = DummyData.rates()
    SearchScreen(
        onQueryChanged = {},
        onClear = {},
        searchUiState = SearchUiState.Loaded(rates = rates),
        navController = rememberNavController()
    )
}

@Preview(showBackground = true)
@Composable
fun SearchScreenEmptyStatePreview() {
    SearchScreen(
        onQueryChanged = {},
        onClear = {},
        searchUiState = SearchUiState.Empty,
        navController = rememberNavController()
    )
}
