package com.client.domain.usecase.home_screen.local_currency

import com.client.data.model.local_rates.LocalRateResponse
import com.client.data.network.asResult
import com.client.data.repository.local_rates.LocalCurrencyRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import com.client.data.network.*
import kotlinx.coroutines.flow.map

class GetLocalCurrencyUseCaseImpl @Inject constructor(
    private val localCurrencyRepository: LocalCurrencyRepository
) : GetLocalCurrencyUseCase {

    override fun getLocalCurrency(): Flow<Result<LocalRateResponse>> = localCurrencyRepository
        .getLivePrice().asResult()
}