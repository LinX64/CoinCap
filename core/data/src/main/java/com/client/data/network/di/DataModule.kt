package com.client.data.network.di

import com.client.data.repository.foreignRates.RatesRepository
import com.client.data.repository.foreignRates.RatesRepositoryImpl
import com.client.data.repository.localRates.LocalCurrencyRepository
import com.client.data.repository.localRates.LocalCurrencyRepositoryImpl
import com.client.data.util.ConnectivityManagerNetworkMonitor
import com.client.data.util.NetworkMonitor
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    @Binds
    fun bindRateRepository(repository: RatesRepositoryImpl): RatesRepository

    @Binds
    fun bindLocalCurrencyRepository(repository: LocalCurrencyRepositoryImpl): LocalCurrencyRepository

    @Binds
    fun bindNetworkMonitor(networkMonitor: ConnectivityManagerNetworkMonitor): NetworkMonitor
}
