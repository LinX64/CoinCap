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
        val ratesList = ratesSet.map {
            val name = it.key
            val sell = it.value.asJsonObject.get("sell").asInt
            val buy = it.value.asJsonObject.get("buy").asInt
            LocalRate(name, sell, buy)
        }
        return LocalRateResponse(ratesList)
    }
}
