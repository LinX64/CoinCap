package com.client.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.client.data.model.Rate
import com.client.data.model.local_rates.LocalRate
import com.client.data.network.Result.*
import com.client.domain.usecase.home_screen.local_currency.GetLocalCurrencyUseCase
import com.client.domain.usecase.home_screen.rates.GetRatesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    getRatesUseCase: GetRatesUseCase,
    getLocalCurrencyUseCase: GetLocalCurrencyUseCase
) : ViewModel() {

    val localLiveRates: StateFlow<HomeLocalUiState> = getLocalCurrencyUseCase
        .getLocalCurrency()
        .map { result ->
            when (result) {
                is Loading -> HomeLocalUiState.Loading
                is Success -> HomeLocalUiState.Success(result.data)
                is Error -> HomeLocalUiState.Error(result.exception.toString())
            }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = HomeLocalUiState.Loading
        )

    val cryptoLiveRates: StateFlow<HomeUiState> = getRatesUseCase
        .getLiveCryptoCurrencies()
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
}

sealed interface HomeUiState {
    object Loading : HomeUiState

    data class Success(
        val rates: List<Rate>
    ) : HomeUiState

    data class Error(val error: String) : HomeUiState
}

sealed interface HomeLocalUiState {
    object Loading : HomeLocalUiState

    data class Success(
        val localRates: List<LocalRate>
    ) : HomeLocalUiState

    data class Error(val error: String) : HomeLocalUiState
}