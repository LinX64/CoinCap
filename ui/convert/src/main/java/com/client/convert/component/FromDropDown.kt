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

@Composable
fun FromDropDown(
    modifier: Modifier = Modifier,
    uiState: ConvertUiState,
    onFromChange: (String) -> Unit
) = when (uiState) {
    is ConvertUiState.Success -> SuccessState(uiState, modifier, onFromChange)
    is ConvertUiState.Error -> Text(text = uiState.error)
    else -> Unit
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun SuccessState(
    uiState: ConvertUiState.Success,
    modifier: Modifier,
    onFromChange: (String) -> Unit
) {
    val rates = uiState.rates
    val options = rates.map { it.symbol }.sortedBy { it }.map { symbol ->
        val getSymbol = symbol.take(2)
        with(getSymbol) {
            val countryName = getCountryName()
            val flag = CountryFlags.getCountryFlagByCode(this)
            "$flag  $symbol - $countryName"
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
                disabledTextColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
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
                        onFromChange(selectionOption)
                    },
                    contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding,
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FromDropDownPreview() {
    val rates = DummyData.rates()
    FromDropDown(
        uiState = ConvertUiState.Success(rates = rates),
        onFromChange = {}
    )
}
