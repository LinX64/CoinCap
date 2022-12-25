package com.client.data.repository.local_rates

import com.client.data.model.local_rates.LocalRateResponse
import com.client.data.network.di.BinDispatchers.*
import com.client.data.network.di.Dispatcher
import com.client.data.retrofit.LocalRatesApi
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class LocalCurrencyRepositoryImpl @Inject constructor(
    @Dispatcher(IO) private val ioDispatcher: CoroutineDispatcher,
    private val localRatesApi: LocalRatesApi
) : LocalCurrencyRepository {

    override fun getLivePrice(): Flow<LocalRateResponse> = flow {
        emit(localRatesApi.getPrices())
    }.flowOn(ioDispatcher)
}