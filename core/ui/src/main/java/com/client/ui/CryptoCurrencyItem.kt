package com.client.ui

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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.graphics.toColorInt
import coil.compose.AsyncImage
import com.client.common.util.Consts
import com.client.common.util.formatToPrice
import com.client.common.util.roundToInteger
import java.util.*

@Composable
fun CryptoCurrencyItem(
    modifier: Modifier = Modifier,
    rate: String,
    symbol: String,
    dollarPrice: String,
    onClick: () -> Unit
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(2.dp)
            .clickable { onClick() },
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background
        )
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
                dollarPrice = dollarPrice
            )
        }
    }
}

@Composable
private fun ColumnItems(
    rate: String,
    symbol: String,
    dollarPrice: String
) {
    Column {
        Text(
            text = symbol,
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(4.dp))

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
                style = MaterialTheme.typography.bodySmall,
                color = if (hadProfit) Color("#4CAF50".toColorInt()) else Color("#E91E63".toColorInt())
            )
            Icon(
                if (hadProfit) Icons.Default.North else Icons.Default.South,
                contentDescription = null,
                modifier = Modifier.size(16.dp),
                tint = if (hadProfit) Color("#4CAF50".toColorInt()) else Color("#E91E63".toColorInt())
            )
        }

        Spacer(modifier = Modifier.height(4.dp))

        val price = rate.toDouble() * dollarPrice.toDouble()
        Text(
            text = price.toInt().formatToPrice() + " t",
            style = MaterialTheme.typography.bodySmall,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
@Preview(showBackground = true)
fun RateCellPreview() {
    CryptoCurrencyItem(
        rate = "1.0",
        symbol = "BTC",
        dollarPrice = "695656566"
    ) {}
}
