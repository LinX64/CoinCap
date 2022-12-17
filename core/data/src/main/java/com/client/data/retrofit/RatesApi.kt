package com.client.data.retrofit

import com.client.data.model.RateDetailResp
import com.client.data.model.RatesResp
import retrofit2.http.GET
import retrofit2.http.Path

interface RatesApi {

    @GET("rates")
    suspend fun getRates(): RatesResp

    @GET("{id}")
    suspend fun getRateBy(@Path("id") id: String): RateDetailResp
}