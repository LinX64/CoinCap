package com.client.data.repository

import com.client.data.model.Rate
import kotlinx.coroutines.flow.Flow

interface RatesRepository {
    fun getRates(): Flow<List<Rate>>
    fun getLiveRates(): Flow<List<Rate>>
    fun getRateBy(id: String): Flow<Rate>
}