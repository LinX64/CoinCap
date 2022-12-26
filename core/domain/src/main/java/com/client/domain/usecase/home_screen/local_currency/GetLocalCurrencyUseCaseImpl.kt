package com.client.domain.usecase.home_screen.local_currency

import com.client.data.model.local_rates.LocalRate
import com.client.data.network.Result
import com.client.data.network.asResult
import com.client.data.repository.local_rates.LocalCurrencyRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetLocalCurrencyUseCaseImpl @Inject constructor(
    private val localCurrencyRepository: LocalCurrencyRepository
) : GetLocalCurrencyUseCase {

    override fun getLocalCurrency(): Flow<Result<List<LocalRate>>> = localCurrencyRepository
        .getLivePrice()
        .asResult()

    //TODO: How about combining the two rates into one?
}