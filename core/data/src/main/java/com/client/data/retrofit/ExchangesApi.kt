package com.client.data.retrofit

import com.client.data.model.ExchangeRateDetailResp
import com.client.data.model.ExchangeRatesResp
import retrofit2.http.GET
import retrofit2.http.Path

interface ExchangesApi {

    @GET("rates")
    suspend fun getExchangeRates(): ExchangeRatesResp

    @GET("{id}")
    suspend fun getExchangeRateBy(@Path("id") id: String): ExchangeRateDetailResp
}