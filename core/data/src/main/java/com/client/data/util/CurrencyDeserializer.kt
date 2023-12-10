package com.client.data.util

import com.client.data.model.localRates.LocalRate
import com.client.data.model.localRates.LocalRateResponse
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type

class CurrencyDeserializer : JsonDeserializer<LocalRateResponse> {

    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): LocalRateResponse {
        if (json == null || context == null) throw NullPointerException("Json or context is null!")

        val ratesSet = json.asJsonObject.entrySet()

        /**
         * filter out the rates which don't have sell & buy values
         * More info: The new way of retrieving the rates is different from the old one.
         * It returns more currencies (gold's price without sell and buy)
         */
        val filteredRatesSet = ratesSet
            .filter {
                val rateObject = it.value.asJsonObject
                rateObject.has("sell") && rateObject.has("buy")
            }
        val ratesList = filteredRatesSet
            .map {
                val name = it.key
                val sell = it.value.asJsonObject.get("sell").asInt
                val buy = it.value.asJsonObject.get("buy").asInt

                LocalRate(
                    code = name,
                    sell = sell,
                    buy = buy
                )
            }
        return LocalRateResponse(ratesList)
    }
}
