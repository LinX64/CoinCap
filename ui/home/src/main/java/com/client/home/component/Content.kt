package com.client.home.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.client.detail.navigation.navigateToDetail
import com.client.home.HomeUiState
import com.client.ui.RateCell

@Composable
internal fun HomeContent(
    modifier: Modifier,
    state: LazyGridState,
    homeUiState: HomeUiState,
    navController: NavHostController
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(200.dp),
        modifier = modifier
            .testTag("ui:home:grid"),
        state = state
    ) {
        homeContent(
            homeUiState = homeUiState,
            onRateClicked = { id -> navController.navigateToDetail(id) }
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
