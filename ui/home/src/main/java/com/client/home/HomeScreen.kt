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
import com.client.data.model.local_rates.LocalRate
import com.client.detail.navigation.navigateToDetail
import com.client.home.component.Header
import com.client.ui.*

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
internal fun HomeRoute(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val homeUiState by viewModel.cryptoLiveRates.collectAsStateWithLifecycle()
    val localRates by viewModel.localLiveRates.collectAsStateWithLifecycle()
    HomeScreen(
        modifier = modifier,
        homeUiState = homeUiState,
        localUiState = localRates,
        navController = navController
    )
}

@Composable
internal fun HomeScreen(
    modifier: Modifier = Modifier,
    homeUiState: HomeUiState,
    localUiState: HomeLocalUiState,
    navController: NavHostController
) {
    val isLoading = homeUiState is HomeUiState.Loading
    val state = rememberLazyGridState()

    TrackScrollJank(scrollableState = state, stateName = "ui:home:grid")

    if (isLoading) LoadingView()

    val rates = (homeUiState as? HomeUiState.Success)?.rates ?: emptyList()
    val localRates = (localUiState as? HomeLocalUiState.Success)?.localRates ?: emptyList()

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        item { Header() }
        item {
            Spacer(modifier = Modifier.height(8.dp))
        }
        item {
            Text(
                text = stringResource(R.string.iranian_rial_title),
                modifier = modifier
                    .fillMaxWidth()
                    .padding(start = 5.dp),
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = stringResource(R.string.iranian_rial_subtitle),
                modifier = modifier
                    .fillMaxWidth()
                    .padding(start = 5.dp, bottom = 8.dp),
                style = MaterialTheme.typography.bodySmall,
                color = Color.Gray
            )
        }
        item {
            LazyRow(modifier = modifier.fillMaxWidth()) {
                items(localRates.size) {
                    val localRate = localRates[it]
                    LocalCurrencyItem(localRate = localRate, onClick = {})
                }
            }
        }
        item {
            Spacer(modifier = Modifier.height(10.dp))
        }
        item {
            Text(
                text = stringResource(id = R.string.top_crypto_currencies),
                modifier = modifier.padding(start = 5.dp),
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(modifier = Modifier.height(3.dp))
            Text(
                text = stringResource(R.string.up_to_date_data),
                modifier = modifier.padding(start = 5.dp, bottom = 10.dp),
                style = MaterialTheme.typography.bodySmall,
                color = Color.Gray
            )
        }
        items(rates.size) {
            val usDollar = localRates.find { rate -> rate.code == "usd" }?.sell ?: 0.0
            val rate = rates[it]

            Column(modifier = modifier.fillMaxWidth()) {
                CryptoCurrencyItem(
                    rate = rate.rateUsd,
                    symbol = rate.symbol,
                    dollarPrice = usDollar.toString()
                ) { navController.navigateToDetail(rate.id) }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen(
        homeUiState = HomeUiState.Success(
            rates = rates()
        ),
        localUiState = HomeLocalUiState.Success(
            localRates = localRates()
        ),
        navController = rememberNavController()
    )
}

private fun rates(): List<Rate> {
    return listOf(
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
}

private fun localRates(): List<LocalRate> {
    return listOf(
        LocalRate(
            code = "US",
            sell = 41000,
            buy = 40800
        ),
        LocalRate(
            code = "EU",
            sell = 41000,
            buy = 40800
        ),
        LocalRate(
            code = "GB",
            sell = 41000,
            buy = 40800
        ),
        LocalRate(
            code = "CA",
            sell = 41000,
            buy = 40800
        ),
        LocalRate(
            code = "AU",
            sell = 41000,
            buy = 40800
        )
    )
}