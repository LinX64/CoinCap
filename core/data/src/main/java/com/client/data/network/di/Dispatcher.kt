package com.client.data.network.di

import javax.inject.Qualifier
import kotlin.annotation.AnnotationRetention.RUNTIME

@Qualifier
@Retention(RUNTIME)
annotation class Dispatcher(val binDispatcher: BinDispatchers)

enum class BinDispatchers {
    Main,
    IO
}
