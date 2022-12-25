package com.client.domain.usecase.home_screen.local_currency

import com.client.data.model.local_rates.LocalRateResponse
import kotlinx.coroutines.flow.Flow
import com.client.data.network.*

interface GetLocalCurrencyUseCase {
    fun getLocalCurrency(): Flow<Result<LocalRateResponse>>
}