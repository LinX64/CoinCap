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
    delayMillis: Long = 3000L,
    maxRetries: Int = Int.MAX_VALUE
): Flow<T> = retryWhen { cause, attempt ->
    if (cause is IOException || attempt < maxRetries) {
        delay(delayMillis)
        true
    } else false
}

fun Double.formatToPrice(): String = String.format("%.2f", this)
fun Double.roundToInteger(): Int = String.format("%.0f", this).toInt()

fun String.capitalize(): String =
    replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.ROOT) else it.toString() }

fun Int.formatToPrice(): String = toString()
    .reversed()
    .chunked(3)
    .joinToString(",")
    .reversed()
