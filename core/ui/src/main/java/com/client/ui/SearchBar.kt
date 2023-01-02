package com.client.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import com.client.coincap.core.ui.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    onQueryChange: (TextFieldValue) -> Unit,
    onClear: () -> Unit
) {
    val query = remember { mutableStateOf(TextFieldValue()) }
    val isQueryEmpty = query.value.text.isEmpty()
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.medium
    ) {
        TextField(
            modifier = modifier.fillMaxWidth(),
            value = query.value,
            onValueChange = {
                query.value = it
                onQueryChange(it)
            },
            placeholder = { if (isQueryEmpty) Text(text = stringResource(R.string.search)) },
            keyboardOptions = keyboardOptions(),
            leadingIcon = { LeadingIcon() },
            trailingIcon = { if (!isQueryEmpty) TrailingIcon(query, onClear) },
            colors = textFieldColors(),
            singleLine = true,
            textStyle = MaterialTheme.typography.bodyMedium
        )
    }
}

@Composable
private fun keyboardOptions() = KeyboardOptions(
    keyboardType = KeyboardType.Text,
    imeAction = ImeAction.Search
)

@Composable
private fun LeadingIcon() {
    Icon(
        imageVector = Icons.Default.Search,
        contentDescription = stringResource(R.string.search),
        tint = Color.Gray
    )
}

@Composable
private fun TrailingIcon(
    query: MutableState<TextFieldValue>,
    onClear: () -> Unit
) {
    AnimatedVisibility(visible = true) {
        IconButton(onClick = {
            query.value = TextFieldValue()
            onClear()
        }) {
            Icon(
                Icons.Filled.Clear,
                contentDescription = null
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun textFieldColors() = TextFieldDefaults.textFieldColors(
    textColor = Color.Gray,
    focusedIndicatorColor = Color.Transparent,
    unfocusedIndicatorColor = Color.Transparent,
    disabledIndicatorColor = Color.Transparent
)

@Composable
@DevicePreviews
fun SearchBarPreview() {
    SearchBar(onQueryChange = {}, onClear = {})
}
