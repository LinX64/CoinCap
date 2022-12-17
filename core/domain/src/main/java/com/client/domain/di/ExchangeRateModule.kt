package com.client.domain.di

import com.client.domain.usecase.GetExchangeRateUseCaseImpl
import com.client.domain.usecase.GetRatesUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface ExchangeRateModule {

    @Binds
    fun bindExchangeUseCase(exchangeRateUseCase: GetExchangeRateUseCaseImpl): GetRatesUseCase
}