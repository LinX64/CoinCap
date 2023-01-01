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
fun FromDropDown(
    modifier: Modifier = Modifier,
    uiState: ConvertUiState
) {
    if (uiState is ConvertUiState.Success) {
        val rates = uiState.rates
        val flags = rates.map {
            CountryFlags.getCountryFlagByCode(it.symbol.take(2))
        }

        val symbols = rates.map { it.symbol }.sortedBy { it }
        val countryNames = rates.map {
            it.symbol.take(2).getCountryName()
        }

        val currency = symbols.find { it == countryNames[0] }
        val options = symbols + countryNames
        var expanded by remember { mutableStateOf(false) }
        var selectedOptionText by remember { mutableStateOf(options[0]) }
        val flag = flags.find { selectedOptionText.contains(it) } ?: ""

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
                leadingIcon = {
                    Text(text = flag)
                },
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                colors = ExposedDropdownMenuDefaults.textFieldColors(
                    textColor = Color.Gray,
                    disabledTextColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                    containerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.1f)
                )
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
                        },
                        contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding,
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FromDropDownPreview() {
    val rates = DummyData.rates()
    FromDropDown(
        uiState = ConvertUiState.Success(rates = rates)
    )
}
