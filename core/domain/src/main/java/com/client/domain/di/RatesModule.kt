package com.client.domain.di

import com.client.domain.usecase.GetRateUseCaseImpl
import com.client.domain.usecase.GetRatesUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RatesModule {

    @Binds
    fun bindRatesUseCase(rateUseCase: GetRateUseCaseImpl): GetRatesUseCase
}