package com.client.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.North
import androidx.compose.material.icons.filled.South
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.graphics.toColorInt
import coil.compose.AsyncImage
import com.client.coincap.core.ui.R
import com.client.common.util.formatToPrice
import com.client.common.util.roundToInteger
import com.client.data.util.Consts
import java.util.*

@Composable
fun RateCell(
    rate: String,
    symbol: String,
    currencySymbol: String,
    type: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(2.dp)
            .clickable { onClick() },
        shape = RoundedCornerShape(8.dp),
    ) {
        Content(currencySymbol, rate, symbol, type)
    }
}

@Composable
fun Content(
    currencySymbol: String,
    rate: String,
    symbol: String,
    type: String
) {
    Row(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            model = Consts.CryptoIconUrl + symbol.lowercase(Locale.ROOT),
            contentDescription = null,
            modifier = Modifier.size(32.dp)
        )

        Spacer(modifier = Modifier.width(16.dp))

        ColumnItems(
            rate = rate,
            symbol = symbol,
            type = type,
            currencySymbol = currencySymbol
        )
    }
}

@Composable
private fun ColumnItems(
    rate: String,
    symbol: String,
    type: String,
    currencySymbol: String
) {
    Column {
        Text(
            text = symbol,
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = "$${rate.toDouble().formatToPrice()}",
            style = MaterialTheme.typography.bodySmall,
            fontWeight = FontWeight.Bold
        )
    }

    Column(
        modifier = Modifier
            .padding(start = 10.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.End
    ) {
        val changedPercentage = (rate.toDouble() * 0.01).roundToInteger()
        val hadProfit = changedPercentage.toDouble() > 0

        Row {
            Text(
                text = "$changedPercentage%",
                style = MaterialTheme.typography.bodyMedium,
                color = if (hadProfit) Color("#4CAF50".toColorInt()) else Color("#E91E63".toColorInt())
            )
            Icon(
                if (hadProfit) Icons.Default.North else Icons.Default.South,
                contentDescription = null,
                modifier = Modifier.size(16.dp),
                tint = if (hadProfit) Color("#4CAF50".toColorInt()) else Color("#E91E63".toColorInt())
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun RateCellPreview() {
    RateCell(
        rate = "1.0",
        symbol = "BTC",
        currencySymbol = "USD",
        type = "Bitcoin",
        onClick = {}
    )
}
