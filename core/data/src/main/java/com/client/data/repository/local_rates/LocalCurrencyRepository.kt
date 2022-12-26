package com.client.data.repository.local_rates

import com.client.data.model.local_rates.LocalRate
import com.client.data.model.local_rates.LocalRateResponse
import kotlinx.coroutines.flow.Flow

interface LocalCurrencyRepository {
    fun getLivePrice(): Flow<List<LocalRate>>
}