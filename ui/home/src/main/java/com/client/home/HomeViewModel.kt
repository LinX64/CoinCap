package com.client.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.client.data.model.Rate
import com.client.data.network.Result
import com.client.data.network.Result.*
import com.client.domain.usecase.home_screen.GetRatesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    getRatesUseCase: GetRatesUseCase,
) : ViewModel() {

    val liveRates: StateFlow<HomeUiState> = getRatesUseCase
        .getLiveCryptoCurrencies()
        .distinctUntilChanged()
        .map { result -> handleState(result) }
        .flatMapLatest {
            flowOf(HomeUiState.Loading, it)
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = HomeUiState.Loading
        )

    private fun handleState(result: Result<List<Rate>>) = when (result) {
        is Loading -> HomeUiState.Loading
        is Success -> HomeUiState.Success(result.data)
        is Error -> HomeUiState.Error(result.exception.toString())
    }
}

sealed interface HomeUiState {
    object Loading : HomeUiState

    data class Success(
        val rates: List<Rate>
    ) : HomeUiState

    data class Error(val error: String) : HomeUiState
}