package com.client.common.util

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn

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