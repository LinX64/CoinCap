package com.client.search.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.client.coincap.core.search.R
import com.client.search.SearchUiState

@Composable
fun InitialView(uiState: SearchUiState) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        if (uiState is SearchUiState.Loading) {
            Text(
                text = stringResource(R.string.search_body_desc),
                style = MaterialTheme.typography.bodyMedium
            )

            Text(
                text = stringResource(R.string.search_body_expl),
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}