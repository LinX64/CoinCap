package com.client.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.client.data.model.Rate
import com.client.data.model.local_rates.LocalRate
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

    private val _localLiveRates = MutableStateFlow<HomeLocalUiState>(HomeLocalUiState.Loading)
    val localLiveRates: StateFlow<HomeLocalUiState> = _localLiveRates.asStateFlow()

    init {
        getLocalCurrencyUseCase.getLocalCurrency()
            .map {
                when (it) {
                    is Loading -> _localLiveRates.value = HomeLocalUiState.Loading
                    is Success -> _localLiveRates.value = HomeLocalUiState.Success(it.data)
                    is Error -> _localLiveRates.value =
                        HomeLocalUiState.Error(it.exception.toString())
                }
            }
            .launchIn(viewModelScope)
    }

    val cryptoLiveRates: StateFlow<HomeUiState> = getRatesUseCase
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