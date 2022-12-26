package com.client.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.graphics.toColorInt
import com.client.common.util.CountryFlags
import com.client.common.util.formatToPrice
import com.client.data.model.local_rates.LocalRate

@Composable
fun LocalCurrencyItem(
    modifier: Modifier = Modifier,
    localRate: LocalRate,
    onClick: () -> Unit = {}
) {
    val code = localRate.code.uppercase().take(2)
    val flag = CountryFlags.getCountryFlagByCode(code)
    val title = localRate.code

    Card(
        modifier = modifier
            .padding(5.dp)
            .width(110.dp)
            .height(120.dp)
            .clickable { onClick() },
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.onSecondary
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        )
    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(text = flag)

            Spacer(modifier = Modifier.height(5.dp))

            Text(
                text = title.uppercase(),
                modifier = modifier.fillMaxWidth(),
                style = MaterialTheme.typography.bodySmall,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(25.dp))

            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp, end = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Bottom
            ) {
                Text(
                    text = "Sell",
                    style = MaterialTheme.typography.bodySmall,
                )

                Text(
                    text = "Buy",
                    style = MaterialTheme.typography.bodySmall,
                )
            }

            Spacer(modifier = Modifier.height(3.dp))

            Row(
                modifier = modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Bottom
            ) {
                Text(
                    text = localRate.sell.formatToPrice(),
                    color = Color("#4CAF50".toColorInt()),
                    style = MaterialTheme.typography.bodySmall,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = localRate.buy.formatToPrice(),
                    color = Color("#E91E63".toColorInt()),
                    style = MaterialTheme.typography.bodySmall,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Composable
@Preview
fun LocalCurrencyPreview() {
    LocalCurrencyItem(
        onClick = {},
        localRate = LocalRate(
            code = "USD",
            buy = 41000,
            sell = 40800
        )
    )
}
