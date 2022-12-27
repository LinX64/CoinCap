package com.client.domain.usecase.home.localCurrency

import com.client.data.model.localRates.LocalRate
import com.client.data.network.Result
import kotlinx.coroutines.flow.Flow

interface GetLocalCurrencyUseCase {
    fun getLocalCurrency(): Flow<Result<List<LocalRate>>>
}
