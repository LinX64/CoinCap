package com.client.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
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
import com.client.coincap.core.ui.R
import com.client.common.util.formatToPrice
import com.client.common.util.roundToInteger
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
            .padding(5.dp)
            .clickable { onClick() },
        shape = RoundedCornerShape(8.dp)
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
        CurrencyBox(currencySymbol = currencySymbol)

        Spacer(modifier = Modifier.width(16.dp))

        ColumnItems(
            rate = rate,
            symbol = symbol,
            type = type
        )
    }
}

@Composable
private fun CurrencyBox(
    modifier: Modifier = Modifier,
    currencySymbol: String
) {
    Box(
        modifier = modifier
            .size(40.dp)
            .fillMaxSize()
            .clip(RoundedCornerShape(8.dp))
            .background(MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.3f)),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = currencySymbol.take(3),
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
private fun ColumnItems(
    rate: String,
    symbol: String,
    type: String
) {
    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = symbol,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = "$${rate.toDouble().formatToPrice()}",
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Bold
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            val capitalisedType =
                type.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.ROOT) else it.toString() }
            Text(
                text = capitalisedType,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Bold,
                color = Color.Gray
            )

            Row {
                val changedPercentage = (rate.toDouble() * 0.01).roundToInteger()
                val hadProfit = changedPercentage.toDouble() > 0

                Text(
                    text = "$changedPercentage%",
                    style = MaterialTheme.typography.bodyMedium,
                    color = if (hadProfit) Color("#4CAF50".toColorInt()) else Color("#E91E63".toColorInt())
                )
                Image(
                    painter = painterResource(
                        id = if (hadProfit) R.drawable.ic_baseline_arrow_upward_24
                        else R.drawable.ic_baseline_arrow_downward_24
                    ),
                    contentDescription = null,
                    modifier = Modifier.size(16.dp)
                )
            }
        }
    }
}

@Composable
@Preview
fun RateCellPreview() {
    RateCell(
        rate = "1.0",
        symbol = "BTC",
        currencySymbol = "USD",
        type = "Bitcoin",
        onClick = {}
    )
}
