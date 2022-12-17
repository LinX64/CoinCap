package com.client.data.model

import com.google.gson.annotations.SerializedName

data class Rate(
    @SerializedName("currencySymbol")
    val currencySymbol: String? = null,
    @SerializedName("id")
    val id: String,
    @SerializedName("rateUsd")
    val rateUsd: String,
    @SerializedName("symbol")
    val symbol: String,
    @SerializedName("type")
    val type: String
)

fun Rate.toExternalModel() = Rate(
    id = id,
    symbol = symbol,
    currencySymbol = currencySymbol,
    type = type,
    rateUsd = rateUsd
)