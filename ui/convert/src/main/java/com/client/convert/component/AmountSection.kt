package com.client.convert.component

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.client.coincap.ui.convert.R

@Composable
fun AmountSection(
    onAmountChange: (String) -> Unit,
    onFromChange: MutableState<String>
) {
    Spacer(modifier = Modifier.height(16.dp))

    Text(
        text = stringResource(R.string.amount),
        style = MaterialTheme.typography.bodyMedium
    )

    Spacer(modifier = Modifier.height(8.dp))

    AmountField(
        onAmountChange = { onAmountChange(it) },
        onFromChanged = onFromChange.value
    )
}
