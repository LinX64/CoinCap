package com.client.data.network.di

import com.client.common.util.Consts
import com.client.data.model.localRates.LocalRateResponse
import com.client.data.retrofit.LocalRatesApi
import com.client.data.retrofit.RatesApi
import com.client.data.util.CurrencyDeserializer
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    @Named(Consts.RATES_API)
    fun provideRatesUrl() = Consts.RATES_API

    @Provides
    @Singleton
    @Named(Consts.LOCAL_RATES_API)
    fun provideLocalUrl() = Consts.LOCAL_RATES_API

    @Provides
    @Singleton
    fun provideHttpClient(): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideGsonConverterFactory(): GsonConverterFactory = GsonConverterFactory.create()

    @Provides
    @Singleton
    @Named(Consts.NORMAL)
    fun provideBaseRetrofit(httpClient: OkHttpClient): Retrofit = Retrofit
        .Builder()
        .client(httpClient)
        .addConverterFactory(provideGsonConverterFactory())
        .baseUrl(provideRatesUrl())
        .build()

    @Provides
    @Singleton
    @Named(Consts.LOCAL_CURRENCY)
    fun provideLocalCurrencyRetrofit(httpClient: OkHttpClient): Retrofit {
        val gson: Gson = GsonBuilder()
            .registerTypeAdapter(LocalRateResponse::class.java, CurrencyDeserializer())
            .create()
        return Retrofit
            .Builder()
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl(provideLocalUrl())
            .build()
    }

    @Provides
    fun provideRatesService(@Named(Consts.NORMAL) retrofit: Retrofit): RatesApi =
        retrofit.create(RatesApi::class.java)

    @Provides
    fun provideLocalRatesService(@Named(Consts.LOCAL_CURRENCY) retrofit: Retrofit): LocalRatesApi =
        retrofit.create(LocalRatesApi::class.java)
}