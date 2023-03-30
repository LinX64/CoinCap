package com.client

import app.cash.turbine.test
import com.client.data.repository.foreignRates.RatesRepository
import com.client.data.repository.foreignRates.RatesRepositoryImpl
import com.client.data.retrofit.RatesApi
import com.client.util.rateDetailRespStub
import com.client.util.ratesResponseStub
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.amshove.kluent.shouldBeEqualTo
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@RunWith(MockitoJUnitRunner::class)
class RatesRepositoryTest {

    @Mock
    private lateinit var ratesApi: RatesApi

    @Mock
    private lateinit var ratesRepository: RatesRepository
    private val testDispatcher = UnconfinedTestDispatcher()

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
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
    fun `When getRateBy() method called THEN should return USD rate`() = runTest {
        val rateId = "USD"
        val getRateBy = ratesApi.getRateBy(rateId)
        whenever(getRateBy).thenReturn(rateDetailRespStub())

        val rate = rateDetailRespStub().rate
        ratesRepository.getRateBy(rateId).test {
            rate.symbol shouldBeEqualTo awaitItem().rate.symbol

            cancelAndConsumeRemainingEvents()
        }

        verify(ratesApi).getRates()
    }
}
