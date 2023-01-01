package com.client.detail

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
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
import com.client.common.util.capitalize
import com.client.data.model.Rate
import com.client.data.model.RateDetailResp
import com.client.detail.component.CandleChart
import com.client.detail.component.DetailCard
import com.client.ui.DevicePreviews
import com.client.ui.ErrorView
import com.client.ui.ProgressBar

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun DetailRoute(
    viewModel: DetailViewModel = hiltViewModel()
) {
    val detailUiState by viewModel.rateUiState.collectAsStateWithLifecycle()
    DetailScreen(detailUiState = detailUiState)
}

@Composable
fun DetailScreen(
    detailUiState: DetailUiState
) {
    when (detailUiState) {
        is DetailUiState.Loading -> ProgressBar()
        is DetailUiState.Success -> DetailView(rateRes = detailUiState.rate)
        is DetailUiState.Error -> ErrorView(errorMessage = detailUiState.error)
    }
}

@Composable
fun DetailView(
    modifier: Modifier = Modifier,
    rateRes: RateDetailResp
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(8.dp)
    ) {
        item {
            CandleChart()

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = rateRes.rate.symbol.capitalize(),
                modifier = modifier
                    .padding(bottom = 10.dp)
                    .fillMaxWidth(),
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold
            )
        }

        item {
            DetailCard(modifier, rateRes)
        }
    }
}

@DevicePreviews
@Composable
fun DetailScreenPreview() {
    DetailScreen(
        detailUiState = DetailUiState.Success(
            RateDetailResp(
                rate = Rate(
                    currencySymbol = "USD",
                    id = "1",
                    rateUsd = "1",
                    symbol = "USD",
                    type = "USD"
                ),
                timestamp = 1
            )
        )
    )
}

@DevicePreviews
@Composable
fun DetailScreenErrorPreview() {
    DetailScreen(detailUiState = DetailUiState.Error("Error"))
}
