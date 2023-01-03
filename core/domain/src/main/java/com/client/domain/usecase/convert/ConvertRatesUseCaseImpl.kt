package com.client.domain.usecase.convert

import com.client.data.model.Rate
import com.client.domain.usecase.rates.GetRatesUseCase
import javax.inject.Inject

class ConvertRatesUseCaseImpl @Inject constructor(
    private val getRatesUseCase: GetRatesUseCase
) {

    operator fun invoke(
        rates: List<Rate>,
        from: String,
        to: String,
        amount: String
    ): String {
        val usdPrice = rates.find { it.symbol == from }?.usdPrice ?: 0.0
        val finalAmount = usdPrice.times(amount.toDouble())
        val toUsdPrice = rates.find { it.symbol == to }?.usdPrice ?: 0.0

        return (finalAmount.div(toUsdPrice)).toString()
    }

    fun getRates() = getRatesUseCase.getRates()
}
