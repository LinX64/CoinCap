package com.client.data.model

import com.google.gson.annotations.SerializedName

data class RateDetailResp(
    @SerializedName("data")
    val rate: Rate,
    @SerializedName("timestamp")
    val timestamp: Long
)

fun RateDetailResp.toExternalModel() = RateDetailResp(
    rate = rate.toExternalModel(),
    timestamp = timestamp
)