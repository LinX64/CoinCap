package com.client.convert.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import com.client.common.util.CountryFlags

@Composable
fun AmountField(
    modifier: Modifier = Modifier,
    onAmountChange: (String) -> Unit,
    onFromChanged: String,
    maxLength: Int = 10,
) {
    var amount by remember { mutableStateOf("") }
    val containerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.1f)
    TextField(
        modifier = modifier.fillMaxWidth(),
        value = amount,
        onValueChange = {
            if (it.length <= maxLength) {
                amount = it
                onAmountChange(it)
            }
        },
        placeholder = { if (amount.isEmpty()) Text(text = "Enter amount") },
        leadingIcon = { LeadingIcon(onFromChanged) },
        trailingIcon = { TrailingIcon(onFromChanged) },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number,
            imeAction = ImeAction.Done
        ),
        singleLine = true,
        colors = TextFieldDefaults.colors(
            focusedContainerColor = containerColor,
            unfocusedContainerColor = containerColor,
            disabledContainerColor = containerColor,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
        )
    )
    // TODO: Use VisualTransformation to format the amount
}

@Composable
private fun LeadingIcon(onFromChanged: String) {
    val flag = onFromChanged.ifEmpty { CountryFlags.getCountryFlagByCode("AE") }
    Text(text = flag)
}

@Composable
private fun TrailingIcon(onFromChanged: String) {
    Text(text = if (onFromChanged.isNotEmpty()) onFromChanged.split(" ")[2] else "AED")
}

@Preview(showBackground = true)
@Composable
fun AmountFieldPreview() {
    AmountField(onAmountChange = {}, onFromChanged = "test")
}
