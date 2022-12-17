package com.client.data.model

import com.google.gson.annotations.SerializedName

data class ExchangeRatesResp(
    @SerializedName("data")
    val rates: List<Rate>,
    @SerializedName("timestamp")
    val timestamp: Long
)