package com.client.convert.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AmountField(
    modifier: Modifier = Modifier
) {
    val amount by remember { mutableStateOf("") }
    TextField(
        modifier = modifier.fillMaxWidth(),
        value = "",
        onValueChange = {},
        placeholder = {
            if (amount.isEmpty()) Text(text = "Enter amount")
        },
        leadingIcon = {
            Text(text = "₹")
        },
        trailingIcon = {
            Text(text = "INR")
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number,
            imeAction = ImeAction.Done
        ),
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color.Gray,
            disabledTextColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            containerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.1f)
        )
    )
}
