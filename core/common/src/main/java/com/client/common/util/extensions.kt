package com.client.common.util

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
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
    } else false
}

/**
 * A function to repeat a flow based on policy & availability of data from the API.
 * Data is available between 9:00 AM & 4:00 PM, Saturday to Thursday (Iran Time), excluding Fridays.
 * For Friday, the data is available from the day before (Thursday).
 * @param [interval] is the time interval between each emission.
 * @param [block] is the block of code to be executed.
 * @return [Flow] of type [T].
 */
fun <T> repeatFlowConditionally(
    interval: Long = Consts.LONG_DELAY,
    day: Int,
    hour: Int,
    dayRange: IntRange = Consts.DAY_RANGE,
    hourRange: IntRange = Consts.HOUR_RANGE,
    block: suspend () -> T
): Flow<T> = flow {
    if (day in dayRange && hour in hourRange) {
        while (true) {
            emit(block.invoke())
            delay(interval)
        }
    } else emit(block.invoke())
}
//TODO: Check to see why this is not working.

/**
 * A function to repeat a flow with a delay.
 * @param [interval] is the time interval between each emission.
 * @param [block] is the block of code to be executed.
 * @return [Flow] of type [T].
 */
fun <T> repeatFlowWithDelay(
    interval: Long,
    block: suspend () -> T
): Flow<T> = flow {
    while (true) {
        emit(block.invoke())
        delay(interval)
    }
}
//TODO: Check to see why this is not working.

fun Double.formatToPrice(): String = String.format("%.4f", this)
fun Double.roundToInteger(): Int = String.format("%.0f", this).toInt()

fun String.capitalize(): String =
    replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.ROOT) else it.toString() }

fun Int.formatToPrice(): String = toString()
    .reversed()
    .chunked(3)
    .joinToString(",")
    .reversed()

