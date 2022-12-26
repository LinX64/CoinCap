package com.client.data.repository.local_rates

import com.client.common.util.CalenderHelper
import com.client.common.util.Consts
import com.client.data.model.local_rates.LocalRate
import com.client.data.network.di.BinDispatchers.*
import com.client.data.network.di.Dispatcher
import com.client.data.retrofit.LocalRatesApi
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

    private val day = CalenderHelper.getDay()
    private val hour = CalenderHelper.getHour()
    private val dayRange = Consts.DAY_RANGE
    private val hourRange = Consts.HOUR_RANGE

    override fun getLivePrice(): Flow<List<LocalRate>> = flow {
        val localRates = localRatesApi.getLocalRates().localRates

        if (day in dayRange && hour in hourRange) {
            while (true) {
                emit(localRates)
                delay(Consts.DELAY)
            }
        } else emit(localRates)
    }
        .flowOn(ioDispatcher)
}