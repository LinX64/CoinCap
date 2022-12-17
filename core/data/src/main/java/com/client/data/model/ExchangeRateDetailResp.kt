package com.client.data.model

import com.google.gson.annotations.SerializedName

data class ExchangeRateDetailResp(
    @SerializedName("data")
    val rate: Rate,
    @SerializedName("timestamp")
    val timestamp: Long
)

fun ExchangeRateDetailResp.toExternalModel() = ExchangeRateDetailResp(
    rate = rate.toExternalModel(),
    timestamp = timestamp
)