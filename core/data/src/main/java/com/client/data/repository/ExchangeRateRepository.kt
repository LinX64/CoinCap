package com.client.data.repository

import com.client.data.model.Rate
import kotlinx.coroutines.flow.Flow

interface ExchangeRateRepository {
    fun getExchangeRates(): Flow<List<Rate>>
    fun getLiveRates(): Flow<List<Rate>>
    fun getExchangeRateBy(id: String): Flow<Rate>
}