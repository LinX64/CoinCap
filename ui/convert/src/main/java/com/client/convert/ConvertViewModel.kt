package com.client.convert

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.client.data.model.Rate
import com.client.data.network.Result
import com.client.domain.usecase.rates.GetRatesUseCaseImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class ConvertViewModel @Inject constructor(
    getRatesUseCaseImpl: GetRatesUseCaseImpl
) : ViewModel() {

    val liveFiats = getRatesUseCaseImpl.getLiveFiatCurrencies()
        .map { result ->
            when (result) {
                is Result.Loading -> ConvertUiState.Loading
                is Result.Success -> ConvertUiState.Success(result.data)
                is Result.Error -> ConvertUiState.Error(result.exception.toString())
            }
        }
        .distinctUntilChanged()
        .flatMapLatest { flowOf(it) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = ConvertUiState.Loading
        )

    fun onAmountChange(amount: String) {
        // TODO
    }

    fun onFromChange(from: String) {
        // TODO
    }

    fun onToChange(to: String) {
        // TODO
    }

    fun onConvertClick() {
        // TODO
    }
}

sealed interface ConvertUiState {
    object Loading : ConvertUiState
    data class Success(val rates: List<Rate>) : ConvertUiState
    data class Error(val error: String) : ConvertUiState
}
