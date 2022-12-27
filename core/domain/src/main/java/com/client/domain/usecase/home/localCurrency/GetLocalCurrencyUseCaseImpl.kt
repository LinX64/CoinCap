package com.client.domain.usecase.home.localCurrency

import com.client.data.model.localRates.LocalRate
import com.client.data.network.Result
import com.client.data.network.asResult
import com.client.data.repository.localRates.LocalCurrencyRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetLocalCurrencyUseCaseImpl @Inject constructor(
    private val localCurrencyRepository: LocalCurrencyRepository
) : GetLocalCurrencyUseCase {

    override fun getLocalCurrency(): Flow<Result<List<LocalRate>>> = localCurrencyRepository
        .getLivePrice()
        .asResult()

    //TODO: Consider combining the two repositories into one in the future.
}