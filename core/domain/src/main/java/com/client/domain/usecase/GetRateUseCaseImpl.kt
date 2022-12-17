package com.client.domain.usecase

import com.client.data.model.Rate
import com.client.data.network.Result
import com.client.data.network.asResult
import com.client.data.repository.RatesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetRateUseCaseImpl @Inject constructor(
    private val rateRepository: RatesRepository
) : GetRatesUseCase {

    operator fun invoke(id: String): Flow<Result<Rate>> = rateRepository
        .getRateBy(id)
        .asResult()

    override fun getRates(): Flow<Result<List<Rate>>> = rateRepository
        .getRates()
        .asResult()

    override fun getLiveRates(): Flow<Result<List<Rate>>> = rateRepository
        .getLiveRates()
        .asResult()
}