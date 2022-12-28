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
    searchRateUseCase: SearchRateUseCase
) : ViewModel() {

    private val _uiState: MutableStateFlow<SearchUiState> = MutableStateFlow(SearchUiState.Loading)
    val uiState: StateFlow<SearchUiState> = _uiState

    init {
        searchRateUseCase
            .getRates()
            .map { result ->
                when (result) {
                    is Result.Loading -> _uiState.value = SearchUiState.Loading
                    is Result.Success -> _uiState.value = SearchUiState.Loaded(result.data)
                    is Result.Error -> _uiState.value =
                        SearchUiState.Error(result.exception.toString())
                }
            }
            .launchIn(viewModelScope)
    }

    fun search(query: String) {
        val state = uiState.value
        flowOf(query)
            .debounce(500)
            .distinctUntilChanged()
            .flatMapLatest {
                flowOf(it)
            }
            .launchIn(viewModelScope)

        if (query.isNotEmpty()) {
            when (state) {
                is SearchUiState.Loaded -> {
                    val filteredRates = flowOf(state.rates)
                        .map {
                            it.filter { rate ->
                                rate.symbol.startsWith(query, ignoreCase = true)
                            }
                        }
                        .map { filteredRates ->
                            if (filteredRates.isNotEmpty()) {
                                _uiState.value = SearchUiState.Success(filteredRates)
                            } else _uiState.value = SearchUiState.Empty
                        }
                        .stateIn(
                            scope = viewModelScope,
                            started = SharingStarted.WhileSubscribed(5_000),
                            initialValue = SearchUiState.Empty
                        )

                    println("filteredRates: $filteredRates")
                }
                else -> Unit
            }
        }

        // TODO: Business logic Should go to use case
    }

    fun onClear() {
        _uiState.value = SearchUiState.Initial
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
