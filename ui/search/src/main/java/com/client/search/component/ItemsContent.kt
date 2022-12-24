package com.client.search.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import com.client.data.model.Rate
import com.client.search.SearchUiState
import com.client.ui.DevicePreviews
import com.client.ui.RateCell

@Composable
internal fun Content(
    modifier: Modifier = Modifier,
    searchUiState: SearchUiState,
    onRateClicked: (String) -> Unit,
) {
    val state = rememberLazyGridState()
    LazyVerticalGrid(
        columns = GridCells.Adaptive(200.dp),
        modifier = modifier
            .fillMaxWidth()
            .testTag("ui:search:grid"),
        state = state
    ) {
        if (searchUiState is SearchUiState.Success) {
            items(searchUiState.rates, key = { it.id }) { rate ->
                RateCell(
                    rate = rate.rateUsd,
                    symbol = rate.symbol,
                    currencySymbol = rate.currencySymbol ?: rate.symbol,
                    type = rate.type,
                    onClick = { onRateClicked(rate.id) })
            }
        }
    }
}

@DevicePreviews
@Composable
fun ItemsContentPreview() {
    val rates = ratesStub()
    Content(
        searchUiState = SearchUiState.Loaded(rates = rates),
        onRateClicked = {}
    )
}

fun ratesStub(): List<Rate> {
    return listOf(
        Rate(
            id = "1",
            symbol = "BTC",
            currencySymbol = "BTC",
            rateUsd = "1.0",
            type = "crypto"
        ),
        Rate(
            id = "2",
            symbol = "ETH",
            currencySymbol = "ETH",
            rateUsd = "1.0",
            type = "crypto"
        ),
        Rate(
            id = "3",
            symbol = "XRP",
            currencySymbol = "XRP",
            rateUsd = "1.0",
            type = "crypto"
        ),
        Rate(
            id = "4",
            symbol = "LTC",
            currencySymbol = "LTC",
            rateUsd = "1.0",
            type = "crypto"
        ),
        Rate(
            id = "5",
            symbol = "BCH",
            currencySymbol = "BCH",
            rateUsd = "1.0",
            type = "crypto"
        ),
        Rate(
            id = "6",
            symbol = "EOS",
            currencySymbol = "EOS",
            rateUsd = "1.0",
            type = "crypto"
        ),
        Rate(
            id = "7",
            symbol = "XLM",
            currencySymbol = "XLM",
            rateUsd = "1.0",
            type = "crypto"
        ),
        Rate(
            id = "8",
            symbol = "ADA",
            currencySymbol = "ADA",
            rateUsd = "1.0",
            type = "crypto"
        ),
        Rate(
            id = "9",
            symbol = "XMR",
            currencySymbol = "XMR",
            rateUsd = "1.0",
            type = "crypto"
        ),
        Rate(
            id = "10",
            symbol = "DASH",
            currencySymbol = "DASH",
            rateUsd = "1.0",
            type = "crypto"
        ),
    )
}