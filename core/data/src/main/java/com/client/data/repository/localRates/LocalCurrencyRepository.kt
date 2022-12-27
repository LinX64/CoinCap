package com.client.data.repository.localRates

import com.client.data.model.localRates.LocalRate
import kotlinx.coroutines.flow.Flow

interface LocalCurrencyRepository {
    fun getLivePrice(): Flow<List<LocalRate>>
}