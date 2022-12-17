package com.client.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.client.ui.ErrorView
import com.client.ui.LoadingView
import com.client.ui.RateCell
import com.client.ui.TrackScrollJank

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
internal fun HomeRoute(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel(),
    navController: NavHostController = rememberNavController()
) {
    val homeUiState by viewModel.liveRates.collectAsStateWithLifecycle()
    HomeScreen(
        modifier = modifier,
        viewModel = viewModel,
        homeUiState = homeUiState,
        navController = navController
    )
}

@Composable
internal fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel,
    homeUiState: HomeUiState,
    navController: NavHostController
) {
    val isLoading = homeUiState is HomeUiState.Loading
    val state = rememberLazyGridState()

    TrackScrollJank(scrollableState = state, stateName = "ui:home:grid")

    LazyVerticalGrid(
        columns = GridCells.Adaptive(200.dp),
        modifier = modifier
            .fillMaxSize()
            .testTag("ui:home:grid"),
        contentPadding = PaddingValues(16.dp),
        state = state
    ) {
        homeContent(
            homeUiState = homeUiState,
            onRateClicked = { id ->
                //TODO - navController.navigateToDetail(rate.id)
            }
        )
    }

    if (isLoading) {
        LoadingView()
    }

    if (homeUiState is HomeUiState.Error) {
        ErrorView(
            modifier = modifier,
            errorMessage = homeUiState.error
        )
    }
}

private fun LazyGridScope.homeContent(
    modifier: Modifier = Modifier,
    homeUiState: HomeUiState,
    onRateClicked: (String) -> Unit
) = when (homeUiState) {
    HomeUiState.Loading -> Unit
    is HomeUiState.Success -> {
        items(homeUiState.rates, key = { it.id }) { rate ->
            Column(modifier = modifier) {
                RateCell(
                    rate = rate.rateUsd,
                    symbol = rate.symbol,
                    currencySymbol = rate.currencySymbol ?: rate.symbol,
                    type = rate.type,
                    onClick = { onRateClicked(rate.id) }
                )
            }
        }
    }
    is HomeUiState.Error -> {
        /* do nothing */
    }
}