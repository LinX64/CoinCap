package com.client.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
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
import com.client.coincap.ui.home.R
import com.client.data.model.Rate
import com.client.home.component.HomeContent
import com.client.home.component.LineChartView
import com.client.ui.*

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
        homeUiState = homeUiState,
        navController = navController
    )
}

@Composable
internal fun HomeScreen(
    modifier: Modifier = Modifier,
    homeUiState: HomeUiState,
    navController: NavHostController
) {
    val isLoading = homeUiState is HomeUiState.Loading
    val state = rememberLazyGridState()

    TrackScrollJank(scrollableState = state, stateName = "ui:home:grid")

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        if (isLoading) LoadingView()

        Spacer(modifier = Modifier.height(16.dp))

        LineChartView()

        Spacer(modifier = modifier.height(16.dp))

        Text(
            text = stringResource(R.string.top_crypto_currencies),
            modifier = modifier
                .padding(bottom = 8.dp)
                .fillMaxWidth(),
            style = MaterialTheme.typography.titleMedium
        )

        HomeContent(modifier, state, homeUiState)
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen(
        homeUiState = HomeUiState.Success(
            rates = listOf(
                Rate(
                    id = "bitcoin",
                    symbol = "BTC",
                    currencySymbol = "USD",
                    rateUsd = "1.0",
                    type = "crypto"
                )
            )
        ),
        navController = rememberNavController()
    )
}