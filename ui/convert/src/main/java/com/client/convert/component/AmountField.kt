package com.client.convert.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import com.client.common.util.CountryFlags

@OptIn(ExperimentalMaterial3Api::class)
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
