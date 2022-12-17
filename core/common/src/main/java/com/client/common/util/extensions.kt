package com.client.common.util

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import java.io.IOException

/**
 * Custom Flow operator to reduce the boilerplate of using stateIn().
 * @param scope is the [CoroutineScope].
 * @param started is the initial value of the [StateFlow], by default it's set to [SharingStarted.WhileSubscribed].
 * @return [StateFlow] with the given [initialValue].
 */
fun <T> Flow<T>.stateInViewModelScope(
    scope: CoroutineScope,
    started: SharingStarted = SharingStarted.WhileSubscribed(5_000),
    initialValue: T
): StateFlow<T> = stateIn(
    scope = scope,
    started = started,
    initialValue = initialValue
)

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