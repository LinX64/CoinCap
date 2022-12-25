package com.client.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.client.data.model.Rate
import com.client.detail.navigation.navigateToDetail
import com.client.home.component.Header
import com.client.ui.*

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
internal fun HomeRoute(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel(),
    navController: NavHostController
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

    LazyColumn(modifier = modifier.fillMaxSize()) {
        item { Header() }
        item {
            Spacer(modifier = Modifier.height(16.dp))
        }
        item {
            Text(
                text = "Iranian Rial Exchange Rates",
                modifier = modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp, top = 16.dp),
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(modifier = Modifier.height(3.dp))
            Text(
                text = "Prices are in Iranian Toman (1 Toman = 10 Rials)",
                modifier = modifier
                    .fillMaxWidth()
                    .padding(start = 13.dp, bottom = 10.dp),
                style = MaterialTheme.typography.titleSmall,
                color = Color.Gray
            )
        }
        item {
            LazyRow(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp)
            ) {
                items(10) {
                    LocalCurrencyItem(onClick = {})
                }
            }
        }
        item {
            Text(
                text = "Top Cryptocurrencies",
                modifier = modifier.padding(start = 10.dp, top = 16.dp),
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(modifier = Modifier.height(3.dp))
            Text(
                text = "Up-to-date prices and market capitalization",
                modifier = modifier.padding(start = 13.dp, bottom = 10.dp),
                style = MaterialTheme.typography.titleSmall,
                color = Color.Gray
            )
        }
        item {
            if (homeUiState is HomeUiState.Success) {
                homeUiState.rates.forEach { rate ->
                    Column(modifier = modifier.padding(start = 10.dp, end = 10.dp)) {
                        RateCell(
                            rate = rate.rateUsd,
                            symbol = rate.symbol,
                            currencySymbol = rate.currencySymbol ?: rate.symbol,
                            type = rate.type,
                            onClick = { navController.navigateToDetail(rate.id) }
                        )
                    }
                }
            }
        }
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
                ),
                Rate(
                    id = "ethereum",
                    symbol = "ETH",
                    currencySymbol = "USD",
                    rateUsd = "1.0",
                    type = "crypto"
                ),
                Rate(
                    id = "dogecoin",
                    symbol = "DOGE",
                    currencySymbol = "USD",
                    rateUsd = "1.0",
                    type = "crypto"
                ),
                Rate(
                    id = "cardano",
                    symbol = "ADA",
                    currencySymbol = "USD",
                    rateUsd = "1.0",
                    type = "crypto"
                ),
                Rate(
                    id = "binancecoin",
                    symbol = "BNB",
                    currencySymbol = "USD",
                    rateUsd = "1.0",
                    type = "crypto"
                ),
                Rate(
                    id = "tether",
                    symbol = "USDT",
                    currencySymbol = "USD",
                    rateUsd = "1.0",
                    type = "crypto"
                )
            )
        ),
        navController = rememberNavController()
    )
}