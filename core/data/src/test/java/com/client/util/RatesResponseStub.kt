package com.client.util

import com.client.data.model.Rate
import com.client.data.model.RatesResp

fun ratesResponseStub() = RatesResp(
    rates = ratesListStub(),
    timestamp = 1234567890
)

fun ratesListStub(count: Int = 10): List<Rate> {
    val result = mutableListOf<Rate>()
    repeat(count) {
        result.add(rateStub())
    }
    return result
}

fun rateStub() = Rate(
    id = "1",
    symbol = "USD",
    currencySymbol = "$",
    type = "fiat",
    rateUsd = "1.0",
    usdPrice = 1000.0
)
