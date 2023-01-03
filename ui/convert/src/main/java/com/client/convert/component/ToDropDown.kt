package com.client.convert.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.client.common.util.CountryFlags
import com.client.common.util.getCountryName
import com.client.convert.ConvertUiState
import com.client.ui.util.DummyData

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ToDropDown(
    modifier: Modifier = Modifier,
    uiState: ConvertUiState,
    onToChange: (String) -> Unit
) {
    var options: List<String> = emptyList()
    if (uiState is ConvertUiState.Success) {
        val rates = uiState.rates
        options = rates.map { it.symbol }.sortedBy { it }.map { symbol ->
            val getSymbol = symbol.take(2)
            with(getSymbol) {
                val countryName = getCountryName()
                val flag = CountryFlags.getCountryFlagByCode(this)
                "$flag  $symbol - $countryName"
            }
        }
    }

    var expanded by remember { mutableStateOf(false) }
    var selectedOptionText by remember { mutableStateOf(options[0]) }
    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded },
    ) {
        TextField(
            modifier = modifier
                .fillMaxWidth()
                .menuAnchor(),
            readOnly = true,
            value = selectedOptionText,
            onValueChange = {},
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
            colors = ExposedDropdownMenuDefaults.textFieldColors(
                textColor = Color.Gray,
                disabledTextColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                containerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.1f)
            ),
            singleLine = true
        )
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
        ) {
            options.forEach { selectionOption ->
                DropdownMenuItem(
                    text = { Text(selectionOption) },
                    onClick = {
                        selectedOptionText = selectionOption
                        expanded = false
                        onToChange(selectionOption)
                    },
                    contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding,
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ToDropDownPreview() {
    val rates = DummyData.rates()
    ToDropDown(
        uiState = ConvertUiState.Success(
            rates = rates
        ),
        onToChange = {}
    )
}
