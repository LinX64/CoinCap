package com.client.data.repository.foreignRates

import com.client.common.util.Consts
import com.client.data.model.Rate
import com.client.data.model.RateDetailResp
import com.client.data.model.toExternalModel
import com.client.data.network.di.BinDispatchers.*
import com.client.data.network.di.Dispatcher
import com.client.data.retrofit.RatesApi
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.retryWhen
import kotlinx.coroutines.withContext
import java.util.concurrent.TimeoutException
import javax.inject.Inject

class RatesRepositoryImpl @Inject constructor(
    @Dispatcher(IO) private val ioDispatcher: CoroutineDispatcher,
    private val ratesApi: RatesApi
) : RatesRepository {

    override fun getRates(): Flow<List<Rate>> = flow {
        emit(getRatesCall())
    }
        .flowOn(ioDispatcher)

    override fun getLiveRates(): Flow<List<Rate>> = flow {
        while (true) {
            emit(getRatesCall())
            delay(Consts.DELAY)
        }
    }
        .retryWhen { cause: Throwable, attempt: Long ->
            cause is TimeoutException && attempt < 3
        }
        .flowOn(ioDispatcher)

    override fun getRateBy(id: String): Flow<RateDetailResp> = flow {
        val rate = ratesApi.getRateBy(id).toExternalModel()
        emit(rate)
    }.flowOn(ioDispatcher)

    private suspend fun getRatesCall(): List<Rate> = withContext(ioDispatcher) {
        return@withContext ratesApi
            .getRates()
            .rates
            .map { it.toExternalModel() }
            .sortedByDescending { it.currencySymbol }
    }
}
