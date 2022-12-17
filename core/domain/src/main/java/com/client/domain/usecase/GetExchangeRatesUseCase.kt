package com.client.domain.usecase

import com.client.data.model.Rate
import com.client.data.network.Result
import kotlinx.coroutines.flow.Flow

interface GetExchangeRatesUseCase {
    fun getRates(): Flow<Result<List<Rate>>>
    fun getLiveRates(): Flow<Result<List<Rate>>>
}
