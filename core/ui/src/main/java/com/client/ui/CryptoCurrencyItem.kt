package com.client.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.North
import androidx.compose.material.icons.filled.South
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
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
import java.util.Locale

@Composable
fun CryptoCurrencyItem(
    modifier: Modifier = Modifier,
    rate: String,
    symbol: String,
    localPrice: String,
    onClick: () -> Unit
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(2.dp)
            .clickable { onClick() },
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
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
                localPrice = localPrice
            )
        }
    }
}

@Composable
private fun ColumnItems(
    rate: String,
    symbol: String,
    localPrice: String
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

        Text(
            text = "$localPrice t",
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
        localPrice = "695656566"
    ) {}
}
