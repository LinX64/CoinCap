package com.client.search

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.client.coincap.core.search.R
import com.client.detail.navigation.navigateToDetail
import com.client.search.component.Content
import com.client.search.component.InitialView
import com.client.search.component.ratesStub
import com.client.ui.DevicePreviews
import com.client.ui.EmptyView
import com.client.ui.ErrorView
import com.client.ui.SearchBar

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun SearchRoute(
    navController: NavHostController,
    viewModel: SearchViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val rates by viewModel.rates.collectAsStateWithLifecycle()

    SearchScreen(
        onQueryChanged = viewModel::search,
        onClear = viewModel::onClear,
        searchUiState = uiState,
        navController = navController
    )
}

@Composable
fun SearchScreen(
    modifier: Modifier = Modifier,
    onQueryChanged: (String) -> Unit,
    onClear: () -> Unit,
    searchUiState: SearchUiState,
    navController: NavHostController
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

        Content(
            searchUiState = searchUiState,
            onRateClicked = { id -> navController.navigateToDetail(id) })

        HandleStates(searchUiState)
    }
}

@Composable
fun HandleStates(searchUiState: SearchUiState) {
    when (searchUiState) {
        is SearchUiState.Error -> ErrorView(errorMessage = searchUiState.message)
        is SearchUiState.Empty -> EmptyView(errorMessage = stringResource(R.string.no_results))
        else -> InitialView(uiState = searchUiState)
    }
}

@DevicePreviews
@Composable
fun SearchScreenPreview() {
    val rates = ratesStub()
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