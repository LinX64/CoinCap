package com.client.data.retrofit

import com.client.data.model.localRates.LocalRateResponse
import retrofit2.http.GET

interface LocalRatesApi {

    @GET("currencies.json")
    suspend fun getLocalRates(): LocalRateResponse
}
