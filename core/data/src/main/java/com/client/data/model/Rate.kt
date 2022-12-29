package com.client.data.model

import com.google.gson.annotations.SerializedName

data class Rate(
    @SerializedName("id")
    val id: String,
    @SerializedName("symbol")
    val symbol: String,
    @SerializedName("currencySymbol")
    val currencySymbol: String? = null,
    @SerializedName("type")
    val type: String,
    @SerializedName("rateUsd")
    val rateUsd: String,
    @SerializedName("usdPrice")
    val usdPrice: Int? = null,
)

fun Rate.toExternalModel() = Rate(
    id = id,
    symbol = symbol,
    currencySymbol = currencySymbol,
    type = type,
    rateUsd = rateUsd,
    usdPrice = usdPrice
)
