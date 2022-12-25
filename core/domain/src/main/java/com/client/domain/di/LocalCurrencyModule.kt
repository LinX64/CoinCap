package com.client.domain.di

import com.client.domain.usecase.home_screen.local_currency.GetLocalCurrencyUseCase
import com.client.domain.usecase.home_screen.local_currency.GetLocalCurrencyUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface LocalCurrencyModule {

    @Binds
    fun bindLocalCurrencyUseCase(localPriceUseCaseImpl: GetLocalCurrencyUseCaseImpl): GetLocalCurrencyUseCase
}