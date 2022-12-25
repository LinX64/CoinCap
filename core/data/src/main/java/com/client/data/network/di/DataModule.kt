package com.client.data.network.di

import com.client.data.repository.foreign_rates.RatesRepository
import com.client.data.repository.foreign_rates.RatesRepositoryImpl
import com.client.data.repository.local_rates.LocalCurrencyRepository
import com.client.data.repository.local_rates.LocalCurrencyRepositoryImpl
import com.client.data.util.ConnectivityManagerNetworkMonitor
import com.client.data.util.NetworkMonitor
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    @Binds
    @Singleton
    fun bindRateRepository(repository: RatesRepositoryImpl): RatesRepository

    @Binds
    @Singleton
    fun bindLocalCurrencyRepository(repository: LocalCurrencyRepositoryImpl): LocalCurrencyRepository

    @Binds
    fun bindNetworkMonitor(networkMonitor: ConnectivityManagerNetworkMonitor): NetworkMonitor
}