package com.client.convert

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.client.data.model.Rate
import com.client.data.network.Result
import com.client.data.network.asResult
import com.client.domain.usecase.convert.ConvertRatesUseCaseImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class ConvertViewModel @Inject constructor(
    private val convertRatesUseCaseImpl: ConvertRatesUseCaseImpl
) : ViewModel() {

    private val fromValue = MutableStateFlow("")
    private val toValue = MutableStateFlow("")
    private val amountValue = MutableStateFlow("")
    val convertResult: MutableState<String> = mutableStateOf("")

    val liveFiats = convertRatesUseCaseImpl.getRates()
        .asResult()
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
        amountValue.value = amount
    }

    fun onFromChange(from: String) {
        fromValue.value = from.split(" ")[2]
    }

    fun onToChange(to: String) {
        toValue.value = to.split(" ")[2]
    }

    fun onConvertClick() {
        if (fromValue.value.isEmpty() ||
            toValue.value.isEmpty() ||
            amountValue.value.isEmpty()
        ) {
            return
        }
        val rates = (liveFiats.value as ConvertUiState.Success).rates
        if (rates.isNotEmpty()) {
            val result = convertRatesUseCaseImpl(
                rates = rates,
                from = fromValue.value,
                to = toValue.value,
                amount = amountValue.value
            )
            convertResult.value = result
        }
    }
}

sealed interface ConvertUiState {
    object Loading : ConvertUiState
    data class Success(val rates: List<Rate>) : ConvertUiState
    data class Error(val error: String) : ConvertUiState
}
