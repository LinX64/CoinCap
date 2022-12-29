package com.client.domain.usecase.home.rates

import com.client.common.util.Consts
import com.client.data.model.Rate
import com.client.data.model.RateDetailResp
import com.client.data.network.Result
import com.client.data.network.asResult
import com.client.data.repository.foreignRates.RatesRepository
import com.client.domain.usecase.home.localCurrency.GetLocalCurrencyUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetRatesUseCaseImpl @Inject constructor(
    private val rateRepository: RatesRepository,
    private val getLocalCurrencyUseCase: GetLocalCurrencyUseCase
) : GetRatesUseCase {

    operator fun invoke(id: String): Flow<Result<RateDetailResp>> =
        rateRepository.getRateBy(id).asResult()

    override fun getRates(): Flow<Result<List<Rate>>> = getCombinedRates().asResult()

    override fun getLiveRates(): Flow<List<Rate>> = rateRepository.getLiveRates()

    override fun getLiveCryptoCurrencies(): Flow<Result<List<Rate>>> {
        return getCombinedRates()
            .map { rates ->
                rates.filter { rate -> rate.type == Consts.CRYPTO_STRING }
            }
            .asResult()
    }

    /**
     * This function combines the live rates from the coincap API with the local currency rates.
     * It basically does a multiplication of the current USD rate of the currency with the local rate.
     * @return Flow<List<Rate>>
     */
    private fun getCombinedRates(): Flow<List<Rate>> {
        val liveRates = getLiveRates()
        val localCurrency = getLocalCurrencyUseCase.getLocalCurrency()

        return combine(liveRates, localCurrency) { rates, localRates ->
            rates.map { rate ->
                val sellPrice = localRates.find { it.code == Consts.USD_STRING }?.sell?.toDouble()
                val inUsdPrice = rate.rateUsd.toDouble()
                val price = sellPrice?.times(inUsdPrice)

                rate.copy(usdPrice = price?.toInt())
            }
        }
    }
}
