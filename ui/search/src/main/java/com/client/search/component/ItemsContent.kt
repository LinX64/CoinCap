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
import com.client.ui.CryptoCurrencyItem
import com.client.ui.DevicePreviews
import com.client.ui.util.DummyData

@Composable
internal fun ItemsContent(
    modifier: Modifier = Modifier,
    rates: List<Rate>,
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
        items(rates, key = { it.id }) { rate ->
            CryptoCurrencyItem(
                rate = rate.rateUsd,
                symbol = rate.symbol,
                dollarPrice = "100"
            ) { onRateClicked(rate.id) }
        }
    }
}

@DevicePreviews
@Composable
fun ItemsContentPreview() {
    val rates = DummyData.rates()
    ItemsContent(
        rates = rates,
        onRateClicked = {}
    )
}


