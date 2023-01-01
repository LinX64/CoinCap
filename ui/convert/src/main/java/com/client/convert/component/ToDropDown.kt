package com.client.convert.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.client.common.util.CountryFlags
import com.client.convert.ConvertUiState
import com.client.ui.util.DummyData

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ToDropDown(
    modifier: Modifier = Modifier,
    uiState: ConvertUiState
) {
    val options = listOf("IRR", "Option 2", "Option 3", "Option 4", "Option 5")
    var expanded by remember { mutableStateOf(false) }
    var selectedOptionText by remember { mutableStateOf(options[0]) }
    val flag = CountryFlags.getCountryFlagByCode("IR")

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
        )
    )
}
