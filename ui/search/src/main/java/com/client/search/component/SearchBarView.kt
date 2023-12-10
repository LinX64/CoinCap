package com.client.search.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.client.coincap.core.ui.R
import com.client.common.util.formatToPrice
import com.client.detail.navigation.navigateToDetail
import com.client.search.SearchUiState
import com.client.ui.AnimatedContent
import com.client.ui.CryptoCurrencyItem
import com.client.ui.DevicePreviews
import com.client.ui.EmptyView
import com.client.ui.ProgressBar
import com.client.coincap.ui.search.R as searchR

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun SearchBarView(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    searchUiState: SearchUiState,
    onQueryChanged: (String) -> Unit,
    onClear: () -> Unit
) {
    val query = remember { mutableStateOf("") }
    val state = rememberLazyGridState()
    val isQueryEmpty = query.value.isEmpty()
    val focusRequester = FocusRequester()

    Column(
        modifier = modifier.semantics { testTag = "SearchScreen" }
            .fillMaxSize()
            .padding(16.dp)
            .focusRequester(focusRequester)
    ) {
        SearchBar(
            modifier = modifier.fillMaxWidth(),
            query = query.value,
            onQueryChange = {
                query.value = it
                onQueryChanged(it)
            },
            onSearch = onQueryChanged,
            placeholder = { Text(text = stringResource(searchR.string.search)) },
            leadingIcon = { LeadingIcon() },
            trailingIcon = { if (!isQueryEmpty) TrailingIcon(onClear = onClear) },
            active = true,
            onActiveChange = {
                query.value = ""
                onClear()
            },
            colors = SearchBarDefaults.colors(
                dividerColor = Color.Transparent
            )
        ) {
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                LazyVerticalGrid(
                    columns = GridCells.Adaptive(300.dp),
                    modifier = modifier.fillMaxWidth(),
                    state = state
                ) {
                    onboardingView(searchUiState, navController)
                }

                when (searchUiState) {
                    is SearchUiState.Loading -> ProgressBar()
                    is SearchUiState.Empty -> EmptyView(
                        errorMessage = stringResource(searchR.string.no_results)
                    )

                    is SearchUiState.Initial -> InitialView()
                    else -> Unit
                }
            }
        }
    }

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }
}

private fun LazyGridScope.onboardingView(
    searchUiState: SearchUiState,
    navController: NavHostController
) = when (searchUiState) {
    is SearchUiState.Loading,
    is SearchUiState.Empty,
    is SearchUiState.Initial -> Unit

    is SearchUiState.Success -> {
        items(searchUiState.rates, key = { it.id }) { rate ->
            CryptoCurrencyItem(
                modifier = Modifier.testTag("search:success"),
                rate = rate.rateUsd,
                symbol = rate.symbol,
                localPrice = rate.usdPrice?.formatToPrice() ?: ""
            ) { navController.navigateToDetail(rate.id) }
        }
    }

    else -> Unit
}

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
    onClear: () -> Unit
) {
    AnimatedContent {
        IconButton(
            onClick = { onClear() }
        ) {
            Icon(
                imageVector = Icons.Default.Clear,
                contentDescription = null
            )
        }
    }
}

@Composable
@DevicePreviews
fun SearchBarPreview() {
    SearchBarView(
        navController = rememberNavController(),
        searchUiState = SearchUiState.Empty,
        onQueryChanged = {}
    ) {}
}
