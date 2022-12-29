package com.client.domain.usecase.home.localCurrency

import com.client.data.model.localRates.LocalRate
import kotlinx.coroutines.flow.Flow

interface GetLocalCurrencyUseCase {
    fun getLocalCurrency(): Flow<List<LocalRate>>
}
