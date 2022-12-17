package com.client.data.network.di

import com.client.data.repository.RatesRepository
import com.client.data.repository.RatesRepositoryImpl
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
    fun bindRateRepository(
        repository: RatesRepositoryImpl
    ): RatesRepository

    @Binds
    fun bindNetworkMonitor(networkMonitor: ConnectivityManagerNetworkMonitor): NetworkMonitor
}