package com.client.domain.usecase.search

import com.client.data.model.Rate
import com.client.domain.usecase.home.rates.GetRatesUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchRateUseCase @Inject constructor(
    private val getRatesUseCase: GetRatesUseCase
) {

    operator fun invoke(query: String, rates: List<Rate>): List<Rate> {
        return rates.filter { it.symbol.startsWith(query, true) }
    }

    fun getRates(): Flow<List<Rate>> = getRatesUseCase.getRates()
}
