package com.client.home.component

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.client.ui.LineChart

@Composable
internal fun LineChartView() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        contentAlignment = Alignment.Center
    ) {
        val mData = listOf(
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
        LineChart(
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
                .align(Alignment.Center),
            data = mData
        )
    }

    Spacer(modifier = Modifier.height(16.dp))

    Text(
        text = "Market Cap (USD) / 24h",
        modifier = Modifier
            .padding(bottom = 8.dp)
            .fillMaxWidth(),
        style = MaterialTheme.typography.bodySmall,
        textAlign = TextAlign.Center
    )
}