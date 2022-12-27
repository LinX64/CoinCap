package com.client.data.repository.foreignRates

import com.client.data.model.Rate
import com.client.data.model.RateDetailResp
import kotlinx.coroutines.flow.Flow

interface RatesRepository {
    fun getRates(): Flow<List<Rate>>
    fun getLiveRates(): Flow<List<Rate>>
    fun getRateBy(id: String): Flow<RateDetailResp>
}