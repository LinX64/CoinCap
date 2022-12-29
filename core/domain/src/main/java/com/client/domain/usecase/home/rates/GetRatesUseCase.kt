package com.client.domain.usecase.home.rates

import com.client.data.model.Rate
import com.client.data.network.Result
import kotlinx.coroutines.flow.Flow

interface GetRatesUseCase {
    fun getRates(): Flow<Result<List<Rate>>>
    fun getLiveRates(): Flow<List<Rate>>
    fun getLiveCryptoCurrencies(): Flow<Result<List<Rate>>>
}
