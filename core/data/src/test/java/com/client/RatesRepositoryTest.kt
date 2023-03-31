package com.client

import app.cash.turbine.test
import com.client.data.model.Rate
import com.client.data.repository.foreignRates.RatesRepository
import com.client.data.repository.foreignRates.RatesRepositoryImpl
import com.client.data.retrofit.RatesApi
import com.client.util.rateDetailRespStub
import com.client.util.ratesResponseStub
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.*
import org.amshove.kluent.shouldBeEqualTo
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.*

@RunWith(MockitoJUnitRunner::class)
class RatesRepositoryTest {

    private val ratesApi = mock<RatesApi>()
    private lateinit var ratesRepository: RatesRepository
    private val testDispatcher = UnconfinedTestDispatcher()

    @Before
    fun setup() {
        ratesRepository = RatesRepositoryImpl(
            ioDispatcher = testDispatcher,
            ratesApi = ratesApi
        )
    }

    @Test
    fun `When getRates() method called THEN should return a list`() = runTest {
        whenever(ratesApi.getRates()).thenReturn(ratesResponseStub())
        val rates = ratesResponseStub().rates

        ratesRepository.getRates().test {
            rates shouldBeEqualTo awaitItem()

            cancelAndConsumeRemainingEvents()
        }

        verify(ratesApi).getRates()
    }

    @Test
    fun `When getLiveRates() is called THEN should emit list of rates with a delay`() =
        runTest(testDispatcher) {
            whenever(ratesApi.getRates()).thenReturn(ratesResponseStub())

            val emissions = mutableListOf<List<Rate>>()
            val job = launch(testDispatcher) {
                ratesRepository.getLiveRates().collect {
                    for (rate in it) {
                        emissions.add(it)
                    }
                }
            }

            delay(1000)

            assertEquals(10, emissions.size)

            job.cancel()
        }

    @Test
    fun `When getRateBy() method called THEN should return USD rate`() = runTest {
        val rateId = "USD"
        whenever(ratesApi.getRateBy(rateId)).thenReturn(rateDetailRespStub())

        val rate = rateDetailRespStub().rate
        ratesRepository.getRateBy(rateId).test {
            rate.symbol shouldBeEqualTo awaitItem().rate.symbol

            cancelAndConsumeRemainingEvents()
        }

        verify(ratesApi).getRateBy(rateId)
    }
}
