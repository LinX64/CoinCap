package com.client.home.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowOutward
import androidx.compose.material.icons.filled.HorizontalRule
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.client.coincap.ui.home.R

@Composable
fun RowButtons(
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(5.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row {
            Icon(
                Icons.Default.Add,
                contentDescription = null,
                modifier = Modifier
                    .size(24.dp)
                    .clip(RoundedCornerShape(25.dp))
                    .background(MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.2f))
                    .padding(5.dp),

            )

            Text(
                text = stringResource(R.string.buy),

                modifier = modifier.padding(start = 5.dp),
            )

            Spacer(modifier = Modifier.width(16.dp))

            Icon(
                Icons.Default.HorizontalRule,
                contentDescription = null,
                modifier = Modifier
                    .size(24.dp)
                    .clip(RoundedCornerShape(25.dp))
                    .background(MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.2f))
                    .padding(5.dp),

            )

            Text(
                text = stringResource(R.string.sell),

                modifier = modifier.padding(start = 5.dp),
            )
        }

        Row {
            Icon(
                Icons.Default.ArrowOutward,
                contentDescription = null,
                modifier = Modifier
                    .size(24.dp)
                    .clip(RoundedCornerShape(25.dp))
                    .background(MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.2f))
                    .padding(5.dp),

            )

            Text(
                text = stringResource(R.string.send),

                modifier = modifier.padding(start = 5.dp),
            )
        }
    }
}

@Preview
@Composable
fun RowButtonsPreview() {
    RowButtons()
}
