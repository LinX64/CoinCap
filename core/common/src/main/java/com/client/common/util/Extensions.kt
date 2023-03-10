package com.client.common.util

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.retryWhen
import java.io.IOException
import java.util.*

/**
 * Flow operator to retry a network call when an [IOException] is thrown.
 * @param [maxRetries] is the maximum number of retries.
 */
fun <T> Flow<T>.retryWithDelay(
    delayMillis: Long = Consts.DELAY,
    maxRetries: Int = Int.MAX_VALUE
): Flow<T> = retryWhen { cause, attempt ->
    if (cause is IOException || attempt < maxRetries) {
        delay(delayMillis)
        true
    } else {
        false
    }
}

fun Double.formatToPrice(): String = String.format(Locale.US, "%,.4f", this)
fun Double.roundToInteger(): Int = String.format(
    Locale.US,
    "%.0f",
    this
).toInt()

fun String.capitalize(): String =
    replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.US) else it.toString() }

fun Int.formatToPrice(): String = toString()
    .reversed()
    .chunked(3)
    .joinToString(",")
    .reversed()

fun String.getCountryName(): String = Locale("", this).displayCountry
fun getCountryCode(countryName: String) =
    Locale.getISOCountries().find { Locale("", it).displayCountry == countryName }
