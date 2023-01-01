package com.client.convert

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.client.common.util.CountryFlags
import com.client.convert.component.AmountField
import com.client.convert.component.ConvertButton
import com.client.convert.component.FromDropDown
import com.client.convert.component.ToDropDown
import com.client.ui.DevicePreviews
import com.client.ui.ProgressBar
import com.client.ui.util.DummyData

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
internal fun ConvertRoute(
    modifier: Modifier = Modifier,
    viewModel: ConvertViewModel = hiltViewModel()
) {
    val uiState by viewModel.liveFiats.collectAsStateWithLifecycle()
    ConvertScreen(
        modifier = modifier,
        uiState = uiState,
        onAmountChange = viewModel::onAmountChange,
        onFromChange = viewModel::onFromChange,
        onToChange = viewModel::onToChange,
        onConvertClick = viewModel::onConvertClick
    )
}

@Composable
internal fun ConvertScreen(
    modifier: Modifier,
    uiState: ConvertUiState,
    onAmountChange: (String) -> Unit = {},
    onFromChange: (String) -> Unit = {},
    onToChange: (String) -> Unit = {},
    onConvertClick: () -> Unit = {}
) {
    val isLoading = uiState is ConvertUiState.Loading
    AnimatedVisibility(
        visible = !isLoading,
        enter = fadeIn(),
        exit = fadeOut()
    ) {
        LazyColumn(modifier = modifier.fillMaxSize()) {
            item {
                ContentView(uiState = uiState)
            }
        }
    }

    if (isLoading) {
        com.client.ui.ProgressBar()
    }
}

@Composable
private fun ContentView(
    modifier: Modifier = Modifier,
    uiState: ConvertUiState
) {
    Card(
        modifier = modifier
            .fillMaxSize()
            .padding(start = 10.dp, end = 10.dp),
        shape = MaterialTheme.shapes.large
    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(start = 16.dp, end = 16.dp, top = 16.dp),
        ) {
            Text(
                text = "Currency \nConverter",
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(24.dp))
            Text(
                text = "From",
                style = MaterialTheme.typography.bodyLarge,
            )

            Spacer(modifier = Modifier.height(8.dp))
            FromDropDown(uiState = uiState)

            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "To",
                style = MaterialTheme.typography.bodyLarge,
            )

            Spacer(modifier = Modifier.height(8.dp))
            ToDropDown(uiState = uiState)

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Amount",
                style = MaterialTheme.typography.bodyLarge
            )
            Spacer(modifier = Modifier.height(8.dp))
            AmountField()

            Spacer(modifier = Modifier.height(32.dp))
            Divider()
            Spacer(modifier = Modifier.height(16.dp))

            ResultText()

            ConvertButton()
        }
    }
}

@Composable
fun ResultText() {
    val flag = CountryFlags.getCountryFlagByCode("IR")
    Text(
        text = "Result",
        modifier = Modifier.fillMaxWidth(),
        textAlign = TextAlign.Center
    )

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = flag,
            modifier = Modifier.padding(top = 8.dp)
        )

        Spacer(modifier = Modifier.width(8.dp))

        Text(
            text = "110,000 IRR",
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.headlineMedium
        )
    }
}

@com.client.ui.DevicePreviews
@Composable
fun ConvertScreenPreview() {
    val rates = DummyData.rates()
    ConvertScreen(
        modifier = Modifier,
        uiState = ConvertUiState.Success(rates),
        onAmountChange = {},
        onFromChange = {},
        onToChange = {},
        onConvertClick = {}
    )
}
