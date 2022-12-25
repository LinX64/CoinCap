package com.client.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.client.data.model.Rate
import com.client.data.model.local_rates.LocalRateResponse
import com.client.data.network.Result.*
import com.client.domain.usecase.home_screen.local_currency.GetLocalCurrencyUseCase
import com.client.domain.usecase.home_screen.rates.GetRatesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    getRatesUseCase: GetRatesUseCase,
    getLocalCurrencyUseCase: GetLocalCurrencyUseCase
) : ViewModel() {

    val liveRates: StateFlow<HomeUiState> = getRatesUseCase
        .getLiveCryptoCurrencies()
        .distinctUntilChanged()
        .map { result ->
            when (result) {
                is Loading -> HomeUiState.Loading
                is Success -> HomeUiState.Success(result.data)
                is Error -> HomeUiState.Error(result.exception.toString())
            }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = HomeUiState.Loading
        )

    val localCurrencyPrices: StateFlow<HomeUiState> = getLocalCurrencyUseCase
        .getLocalCurrency()
        .map { result ->
            when (result) {
                is Loading -> HomeUiState.Loading
                is Success -> HomeUiState.Loaded(result.data)
                is Error -> HomeUiState.Error(result.exception.toString())
            }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = HomeUiState.Loading
        )
}

sealed interface HomeUiState {
    object Loading : HomeUiState

    data class Success(
        val rates: List<Rate>
    ) : HomeUiState

    data class Loaded(
        val rates: LocalRateResponse
    ) : HomeUiState

    data class Error(val error: String) : HomeUiState
}