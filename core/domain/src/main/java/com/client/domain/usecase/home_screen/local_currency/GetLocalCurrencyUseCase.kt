package com.client.domain.usecase.home_screen.local_currency

import com.client.data.model.local_rates.LocalRate
import com.client.data.network.Result
import kotlinx.coroutines.flow.Flow

interface GetLocalCurrencyUseCase {
    fun getLocalCurrency(): Flow<Result<List<LocalRate>>>
}