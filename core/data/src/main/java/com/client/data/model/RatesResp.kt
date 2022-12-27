package com.client.data.model

import com.google.gson.annotations.SerializedName

data class RatesResp(
    @SerializedName("data")
    val rates: List<Rate>,
    @SerializedName("timestamp")
    val timestamp: Long
)
