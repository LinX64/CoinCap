package com.client.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.client.data.model.Rate
import com.client.data.network.Result
import com.client.domain.usecase.search.SearchRateUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchRateUseCase: SearchRateUseCase
) : ViewModel() {

    private val _searchResultUiState: MutableStateFlow<SearchUiState> =
        MutableStateFlow(SearchUiState.Loading)
    val searchResultUiState = _searchResultUiState.asStateFlow()

    val rates: StateFlow<SearchUiState> = searchRateUseCase
        .getRates()
        .map { result -> handleState(result) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = SearchUiState.Loading
        )

    fun search(query: String) {
        val uiState = rates.value
        if (uiState is SearchUiState.Loaded) {
            searchRateUseCase(query, uiState.rates)
                .onStart { _searchResultUiState.value = SearchUiState.Initial }
                .map { rates ->
                    when {
                        rates.isNotEmpty() -> _searchResultUiState.value =
                            SearchUiState.Success(rates)
                        else -> _searchResultUiState.value = SearchUiState.Empty
                    }
                }
                .launchIn(viewModelScope)
        }
    }

    private fun handleState(result: Result<List<Rate>>) = when (result) {
        is Result.Loading -> SearchUiState.Loading
        is Result.Success -> SearchUiState.Loaded(result.data)
        is Result.Error -> SearchUiState.Error(result.exception.toString())
    }
}

sealed interface SearchUiState {
    object Initial : SearchUiState
    object Empty : SearchUiState
    object Loading : SearchUiState

    data class Loaded(
        val rates: List<Rate>
    ) : SearchUiState

    data class Success(
        val rates: List<Rate>
    ) : SearchUiState

    data class Error(val message: String) : SearchUiState
}