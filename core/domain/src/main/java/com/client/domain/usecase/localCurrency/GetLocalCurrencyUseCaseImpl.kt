package com.client.domain.usecase.localCurrency

import com.client.data.model.localRates.LocalRate
import com.client.data.repository.localRates.LocalCurrencyRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetLocalCurrencyUseCaseImpl @Inject constructor(
    private val localCurrencyRepository: LocalCurrencyRepository
) : GetLocalCurrencyUseCase {

    override fun getLocalCurrency(): Flow<List<LocalRate>> = localCurrencyRepository
        .getLivePrice()
}
