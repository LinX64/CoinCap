package com.client.data.repository.local_rates

import com.client.data.model.local_rates.LocalRate
import com.client.data.network.di.BinDispatchers.*
import com.client.data.network.di.Dispatcher
import com.client.data.retrofit.LocalRatesApi
import com.client.data.util.Consts.DELAY
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class LocalCurrencyRepositoryImpl @Inject constructor(
    @Dispatcher(IO) private val ioDispatcher: CoroutineDispatcher,
    private val localRatesApi: LocalRatesApi
) : LocalCurrencyRepository {

    override fun getLivePrice(): Flow<List<LocalRate>> = flow {
        while (true) {
            val localRates = localRatesApi.getPrices().localRates
            emit(localRates)
            delay(DELAY)
        }
    }.flowOn(ioDispatcher)
}