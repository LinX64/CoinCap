package com.client.convert

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.client.convert.component.AmountField
import com.client.convert.component.AmountSection
import com.client.convert.component.BottomSection
import com.client.convert.component.ConvertButton
import com.client.convert.component.FromDropDown
import com.client.convert.component.Header
import com.client.convert.component.ResultText
import com.client.convert.component.ToDropDown
import com.client.convert.component.ToSection
import com.client.ui.AnimatedContent
import com.client.ui.ProgressBar
import com.client.ui.util.DummyData

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
    AnimatedContent {
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
