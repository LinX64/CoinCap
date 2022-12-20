package com.client.detail.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.client.data.model.Rate
import com.client.data.model.RateDetailResp
import com.client.ui.LineChart

@Composable
internal fun CandleChart(
    modifier: Modifier = Modifier,
    rateRes: RateDetailResp
) {
    val data = listOf(
        Pair(6, 111.45),
        Pair(7, 111.0),
        Pair(8, 113.45),
        Pair(9, 112.25),
        Pair(10, 116.45),
        Pair(11, 113.35),
        Pair(12, 118.65),
        Pair(13, 110.15),
        Pair(14, 113.05),
        Pair(15, 114.25),
        Pair(16, 116.35),
        Pair(17, 117.45)
    )
    Box(
        modifier = modifier
            .fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        LineChart(
            modifier = Modifier
                .fillMaxWidth()
                .size(150.dp)
                .padding(16.dp),
            data = data
        )
    }
}

@Composable
@Preview
private fun PreviewCandleChart() {
    CandleChart(
        modifier = Modifier,
        rateRes = RateDetailResp(
            rate = Rate(
                id = "id",
                symbol = "symbol",
                type = "type",
                currencySymbol = "currencySymbol",
                rateUsd = "12232",
            ), timestamp = 324234234234
        )
    )
}