package com.client.domain.usecase.home.rates

import com.client.data.model.Rate
import com.client.data.model.RateDetailResp
import com.client.data.network.Result
import com.client.data.network.asResult
import com.client.data.repository.foreignRates.RatesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetRatesUseCaseImpl @Inject constructor(
    private val rateRepository: RatesRepository
) : GetRatesUseCase {

    operator fun invoke(id: String): Flow<Result<RateDetailResp>> = rateRepository
        .getRateBy(id)
        .asResult()

    override fun getRates(): Flow<List<Rate>> = rateRepository
        .getRates()

    override fun getLiveRates(): Flow<Result<List<Rate>>> = rateRepository
        .getLiveRates()
        .asResult()

    override fun getLiveCryptoCurrencies(): Flow<Result<List<Rate>>> = rateRepository
        .getLiveRates()
        .map { rates ->
            rates.filter { rate -> rate.type == "crypto" }
        }
        .asResult()
}
