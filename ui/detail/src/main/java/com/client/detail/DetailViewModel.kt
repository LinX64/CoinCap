package com.client.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.client.data.model.RateDetailResp
import com.client.data.network.Result
import com.client.detail.navigation.DetailArgs
import com.client.domain.usecase.home.rates.GetRatesUseCaseImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    getRatesUseCaseImpl: GetRatesUseCaseImpl
) : ViewModel() {

    private val rateId: String = DetailArgs(savedStateHandle).rateId
    val rateRespUiState: StateFlow<DetailUiState> = getRatesUseCaseImpl(rateId)
        .map { result ->
            when (result) {
                is Result.Success -> DetailUiState.Success(result.data)
                is Result.Error -> DetailUiState.Error(result.exception.toString())
                is Result.Loading -> DetailUiState.Loading
            }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = DetailUiState.Loading
        )
}

sealed interface DetailUiState {
    object Loading : DetailUiState
    data class Success(val rate: RateDetailResp) : DetailUiState
    data class Error(val error: String) : DetailUiState
}
