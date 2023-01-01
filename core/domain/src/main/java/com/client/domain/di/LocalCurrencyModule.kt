package com.client.domain.di

import com.client.domain.usecase.localCurrency.GetLocalCurrencyUseCase
import com.client.domain.usecase.localCurrency.GetLocalCurrencyUseCaseImpl
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
