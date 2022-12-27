package com.client.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDownward
import androidx.compose.material.icons.filled.ArrowUpward
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.graphics.toColorInt
import com.client.coincap.core.ui.R
import com.client.common.util.CountryFlags
import com.client.common.util.formatToPrice
import com.client.data.model.localRates.LocalRate

@Composable
fun LocalCurrencyItem(
    modifier: Modifier = Modifier,
    localRate: LocalRate,
    onClick: () -> Unit
) {
    val code = localRate.code.uppercase().take(2)
    val flag = CountryFlags.getCountryFlagByCode(code)
    val title = localRate.code

    Card(
        modifier = modifier
            .padding(5.dp)
            .width(120.dp)
            .height(135.dp)
            .clickable { onClick() },
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.onSecondary
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        )
    ) {
        CardContent(modifier, flag, title, localRate)
    }
}

@Composable
private fun CardContent(
    modifier: Modifier,
    flag: String,
    title: String,
    localRate: LocalRate
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(10.dp)
    ) {
        Text(
            text = flag,
            modifier = modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = title.uppercase(),
            modifier = modifier.fillMaxWidth(),
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(16.dp))

        Column(
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Bottom
        ) {
            Row(
                modifier = modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowUpward,
                    contentDescription = null,
                    tint = Color("#E91E63".toColorInt()),
                    modifier = modifier.size(16.dp)
                )
                Text(
                    text = stringResource(R.string.sell),
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Gray,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.width(10.dp))

                Icon(
                    imageVector = Icons.Default.ArrowDownward,
                    contentDescription = null,
                    tint = Color("#4CAF50".toColorInt()),
                    modifier = modifier.size(16.dp)
                )
                Text(
                    text = stringResource(R.string.buy),
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Gray,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(3.dp))

            Row(
                modifier = modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = localRate.sell.formatToPrice(),
                    color = Color("#E91E63".toColorInt()),
                    style = MaterialTheme.typography.bodySmall,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = localRate.buy.formatToPrice(),
                    color = Color("#4CAF50".toColorInt()),
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
        localRate = LocalRate(
            code = "USD",
            buy = 41000,
            sell = 40800
        ),
        onClick = {}
    )
}
