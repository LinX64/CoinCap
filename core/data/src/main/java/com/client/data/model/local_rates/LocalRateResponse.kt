package com.client.data.model.local_rates

import com.google.gson.annotations.SerializedName

data class LocalRateResponse(
    @SerializedName("")
    val localRates: List<LocalRate>
)

data class LocalRate(
    @SerializedName("code")
    val code: String,
    @SerializedName("sell")
    val sell: Int,
    @SerializedName("buy")
    val buy: Int
)