package com.client.domain.usecase.search

import com.client.data.model.Rate
import com.client.data.network.Result
import com.client.domain.usecase.home.rates.GetRatesUseCase
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class SearchRateUseCase @Inject constructor(
    private val getRatesUseCase: GetRatesUseCase
) {

    operator fun invoke(query: String, rates: List<Rate>): Flow<List<Rate>> {
        return flowOf(rates)
            .debounce(500)
            .distinctUntilChanged()
            .map { ratesList ->
                ratesList.filter { rate ->
                    rate.symbol.startsWith(
                        query, ignoreCase = true
                    )
                }
            }
    }

    fun getRates(): Flow<Result<List<Rate>>> = getRatesUseCase.getRates()
}