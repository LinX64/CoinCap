package com.client.convert

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.client.convert.component.*
import com.client.ui.ProgressBar
import com.client.ui.util.DummyData

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
internal fun ExchangeRoute(
    modifier: Modifier = Modifier,
    viewModel: ConvertViewModel = hiltViewModel()
) {
    val uiState by viewModel.liveFiats.collectAsStateWithLifecycle()
    ExchangeScreen(
        modifier = modifier,
        uiState = uiState,
        onAmountChange = viewModel::onAmountChange,
        onFromChange = viewModel::onFromChange,
        onToChange = viewModel::onToChange,
        onConvertClick = viewModel::onConvertClick,
        convertResult = viewModel.convertResult.value
    )
}

@Composable
internal fun ExchangeScreen(
    modifier: Modifier,
    uiState: ConvertUiState,
    onAmountChange: (String) -> Unit,
    onFromChange: (String) -> Unit,
    onToChange: (String) -> Unit,
    onConvertClick: () -> Unit,
    convertResult: String = ""
) {
    val isLoading = uiState is ConvertUiState.Loading
    val amount by remember { mutableStateOf("") }
    AnimatedVisibility(
        visible = !isLoading,
        enter = fadeIn(),
        exit = fadeOut()
    ) {
        LazyColumn(modifier = modifier.fillMaxSize()) {
            item {
                Card(
                    modifier = modifier
                        .fillMaxSize()
                        .padding(start = 10.dp, end = 10.dp),
                    shape = MaterialTheme.shapes.large
                ) {
                    Column(
                        modifier = modifier
                            .fillMaxSize()
                            .padding(start = 16.dp, end = 16.dp, top = 16.dp)
                    ) {
                        Header()

                        FromDropDown(
                            uiState = uiState,
                            onFromChange = { onFromChange(it) }
                        )

                        ToSection()

                        ToDropDown(
                            uiState = uiState,
                            onToChange = { onToChange(it) }
                        )

                        AmountSection()

                        AmountField(
                            onAmountChange = { onAmountChange(it) },
                            onFromChanged = amount
                        )

                        BottomSection()

                        ResultText(result = convertResult)

                        ConvertButton(onConvertClicked = onConvertClick)
                    }
                }
            }
        }
    }

    if (isLoading) {
        ProgressBar()
    }
}

@Preview(showBackground = true)
@Composable
fun ConvertScreenPreview() {
    val rates = DummyData.rates()
    ExchangeScreen(
        modifier = Modifier,
        uiState = ConvertUiState.Success(rates),
        onAmountChange = {},
        onFromChange = {},
        onToChange = {},
        onConvertClick = {},
        convertResult = ""
    )
}
