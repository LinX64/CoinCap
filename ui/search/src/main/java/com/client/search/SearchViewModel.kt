package com.client.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.client.data.model.Rate
import com.client.data.network.Result
import com.client.data.network.asResult
import com.client.domain.usecase.search.SearchRateUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchRateUseCase: SearchRateUseCase
) : ViewModel() {

    private val _uiState: MutableStateFlow<SearchUiState> = MutableStateFlow(SearchUiState.Initial)
    val uiState: StateFlow<SearchUiState> = _uiState.asStateFlow()
    private val searchQuery = MutableStateFlow("")

    fun search(query: String) {
        with(searchQuery) {
            value = query.trim()
            debounce(500)
                .filterNotNull()
                .distinctUntilChanged()
                .flatMapLatest {
                    searchRateUseCase.getRates().map { result -> handleState(result) }
                }
                .launchIn(viewModelScope)
        }
    }

    private fun handleState(result: Result<List<Rate>>) = when (result) {
        is Result.Success -> {
            val filteredRates = searchRateUseCase(searchQuery.value, result.data)
            if (filteredRates.isNotEmpty()) {
                _uiState.value = SearchUiState.Success(filteredRates)
            } else {
                _uiState.value = SearchUiState.Empty
            }
        }
        is Result.Error -> {
            _uiState.value = SearchUiState.Error(result.exception.toString())
        }
        is Result.Loading -> _uiState.value = SearchUiState.Loading
    }

    fun onClear() {
        searchQuery.value = ""
        _uiState.value = SearchUiState.Initial
    }
}

sealed interface SearchUiState {
    object Initial : SearchUiState
    object Empty : SearchUiState
    object Loading : SearchUiState

    data class Success(
        val rates: List<Rate>
    ) : SearchUiState

    data class Error(val message: String) : SearchUiState
}
