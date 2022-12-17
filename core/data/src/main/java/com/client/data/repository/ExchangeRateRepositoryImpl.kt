package com.client.data.repository

import com.client.data.model.Rate
import com.client.data.model.toExternalModel
import com.client.data.network.di.BinDispatchers.*
import com.client.data.network.di.Dispatcher
import com.client.data.retrofit.ExchangesApi
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.retryWhen
import java.io.IOException
import javax.inject.Inject

class ExchangeRateRepositoryImpl @Inject constructor(
    @Dispatcher(IO) private val ioDispatcher: CoroutineDispatcher,
    private val exchangesApi: ExchangesApi
) : ExchangeRateRepository {

    companion object {
        private const val DELAY = 3000L
    }

    override fun getExchangeRates(): Flow<List<Rate>> = flow {
        emit(getRatesCall())
    }.flowOn(ioDispatcher)

    override fun getLiveRates(): Flow<List<Rate>> = flow {
        while (true) {
            emit(getRatesCall())
            delay(DELAY)
        }
    }
        .retryWhen { cause: Throwable, attempt: Long ->
            cause is IOException && attempt < 10
        }
        .flowOn(ioDispatcher)

    override fun getExchangeRateBy(id: String) = flow {
        val rate = exchangesApi.getExchangeRateBy(id).rate
        emit(rate)
    }.flowOn(ioDispatcher)

    private suspend fun getRatesCall() = exchangesApi
        .getExchangeRates()
        .rates
        .map { it.toExternalModel() }
        .sortedBy { it.currencySymbol }
}