package com.client.domain.di

import com.client.domain.usecase.localCurrency.GetLocalCurrencyUseCase
import com.client.domain.usecase.localCurrency.GetLocalCurrencyUseCaseImpl
import com.client.domain.usecase.rates.GetRatesUseCase
import com.client.domain.usecase.rates.GetRatesUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
interface DataModule {

    @Binds
    fun bindLocalCurrencyUseCase(localPriceUseCaseImpl: GetLocalCurrencyUseCaseImpl): GetLocalCurrencyUseCase

    @Binds
    fun bindRatesUseCase(rateUseCase: GetRatesUseCaseImpl): GetRatesUseCase
}
