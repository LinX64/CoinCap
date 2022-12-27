package com.client.ui.util

import com.client.data.model.Rate
import com.client.data.model.localRates.LocalRate

object DummyData {

    fun rates(): List<Rate> {
        return listOf(
            Rate(
                id = "bitcoin",
                symbol = "BTC",
                currencySymbol = "USD",
                rateUsd = "1.0",
                type = "crypto"
            ),
            Rate(
                id = "ethereum",
                symbol = "ETH",
                currencySymbol = "USD",
                rateUsd = "1.0",
                type = "crypto"
            ),
            Rate(
                id = "dogecoin",
                symbol = "DOGE",
                currencySymbol = "USD",
                rateUsd = "1.0",
                type = "crypto"
            ),
            Rate(
                id = "cardano",
                symbol = "ADA",
                currencySymbol = "USD",
                rateUsd = "1.0",
                type = "crypto"
            ),
            Rate(
                id = "binancecoin",
                symbol = "BNB",
                currencySymbol = "USD",
                rateUsd = "1.0",
                type = "crypto"
            ),
            Rate(
                id = "tether",
                symbol = "USDT",
                currencySymbol = "USD",
                rateUsd = "1.0",
                type = "crypto"
            )
        )
    }

    fun localRates(): List<LocalRate> {
        return listOf(
            LocalRate(
                code = "US",
                sell = 41000,
                buy = 40800
            ),
            LocalRate(
                code = "EU",
                sell = 41000,
                buy = 40800
            ),
            LocalRate(
                code = "GB",
                sell = 41000,
                buy = 40800
            ),
            LocalRate(
                code = "CA",
                sell = 41000,
                buy = 40800
            ),
            LocalRate(
                code = "AU",
                sell = 41000,
                buy = 40800
            )
        )
    }
}
