package com.client.search

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
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
import com.client.search.component.InitialView
import com.client.ui.*
import com.client.ui.util.DummyData

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun SearchRoute(
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
fun SearchScreen(
    modifier: Modifier = Modifier,
    searchUiState: SearchUiState,
    navController: NavHostController,
    onQueryChanged: (String) -> Unit,
    onClear: () -> Unit
) {
    val state = rememberLazyGridState()
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

        LazyVerticalGrid(
            columns = GridCells.Adaptive(200.dp),
            modifier = modifier
                .fillMaxWidth()
                .testTag("ui:search:grid"),
            state = state
        ) {
            onboardingView(searchUiState, navController)
        }

        when (searchUiState) {
            is SearchUiState.Loading -> ProgressBar()
            is SearchUiState.Empty -> EmptyView(errorMessage = stringResource(id = R.string.no_results))
            is SearchUiState.Initial -> InitialView()
            else -> Unit
        }
    }
}

private fun LazyGridScope.onboardingView(
    searchUiState: SearchUiState,
    navController: NavHostController
) {
    when (searchUiState) {
        is SearchUiState.Loading,
        is SearchUiState.Empty,
        is SearchUiState.Initial -> Unit
        is SearchUiState.Success -> {
            items(searchUiState.rates, key = { it.id }) { rate ->
                CryptoCurrencyItem(
                    rate = rate.rateUsd,
                    symbol = rate.symbol,
                    dollarPrice = "100"
                ) { navController.navigateToDetail(rate.id) }
            }
        }
        else -> {}
    }
}

@DevicePreviews
@Composable
fun SearchScreenPreview() {
    val rates = DummyData.rates()
    SearchScreen(
        onQueryChanged = {},
        onClear = {},
        searchUiState = SearchUiState.Success(rates = rates),
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
