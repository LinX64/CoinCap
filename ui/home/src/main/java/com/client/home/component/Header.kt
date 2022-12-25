package com.client.home.component

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.North
import androidx.compose.material.icons.filled.South
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.graphics.toColorInt
import com.client.coincap.ui.home.R
import com.client.common.util.roundToInteger

@Composable
fun Header(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.padding(horizontal = 5.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Total balance",
            style = MaterialTheme.typography.titleSmall,
            color = Color.Gray
        )

        Spacer(modifier = Modifier.height(5.dp))

        Text(
            text = "$1,000.00",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
        )

        Spacer(modifier = Modifier.height(5.dp))

        Row(
            modifier = modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            val rate = 1100.00
            val changedPercentage = (rate * 0.01).roundToInteger()
            val hadProfit = changedPercentage.toDouble() > 0

            Icon(
                if (hadProfit) Icons.Default.North else Icons.Default.South,
                contentDescription = null,
                modifier = Modifier.size(16.dp),
                tint = if (hadProfit) Color("#4CAF50".toColorInt()) else Color("#E91E63".toColorInt())
            )

            Text(
                text = "$5,546.91" + " • " + "50%",
                style = MaterialTheme.typography.bodyMedium,
                color = if (hadProfit) Color("#4CAF50".toColorInt()) else Color("#E91E63".toColorInt())
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        PortfolioCard(modifier)
    }
}

@Composable
private fun PortfolioCard(modifier: Modifier) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
            .height(160.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color("#014B35".toColorInt())
        )
    ) {
        Column(modifier = modifier.padding(16.dp)) {
            Row(
                modifier = modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = stringResource(R.string.holding_portfolio),
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.White

                )

                Icon(Icons.Default.MoreVert, contentDescription = null, tint = Color.White)
            }

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "$1,200.00",
                style = MaterialTheme.typography.bodyLarge,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(5.dp))

            BalancesRow(modifier)

            Spacer(modifier = Modifier.height(18.dp))

            RowButtons()
        }
    }
}

@Composable
private fun BalancesRow(modifier: Modifier) {
    Row(
        modifier = modifier
    ) {
        val rate = 1.0
        val changedPercentage = (rate * 0.01).roundToInteger()
        val hadProfit = changedPercentage.toDouble() > 0

        Icon(
            if (hadProfit) Icons.Default.North else Icons.Default.South,
            contentDescription = null,
            modifier = Modifier.size(16.dp),
            tint = if (hadProfit) Color("#4CAF50".toColorInt()) else Color("#E91E63".toColorInt())
        )

        Text(
            text = "$5,546.91" + " • " + "67%",
            style = MaterialTheme.typography.bodySmall,
            color = if (hadProfit) Color("#4CAF50".toColorInt()) else Color("#E91E63".toColorInt())
        )
    }
}

@Preview(showBackground = true)
@Composable
fun HeaderPreview() {
    Header()
}