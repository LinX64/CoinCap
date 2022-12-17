package com.client.domain.usecase

import com.client.data.model.Rate
import com.client.data.network.Result
import com.client.data.network.asResult
import com.client.data.repository.ExchangeRateRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetExchangeRateUseCaseImpl @Inject constructor(
    private val exchangeRateRepository: ExchangeRateRepository
) : GetExchangeRatesUseCase {

    operator fun invoke(id: String): Flow<Result<Rate>> = exchangeRateRepository
        .getExchangeRateBy(id)
        .asResult()

    override fun getRates(): Flow<Result<List<Rate>>> = exchangeRateRepository
        .getExchangeRates()
        .asResult()

    override fun getLiveRates(): Flow<Result<List<Rate>>> = exchangeRateRepository
        .getLiveRates()
        .asResult()
}