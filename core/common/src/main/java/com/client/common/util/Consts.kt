package com.client.common.util

object Consts {
    const val RATES_API = "https://api.coincap.io/v2/"
    const val LOCAL_RATES_API = "https://bonbast-api.deta.dev/"
    const val CryptoIconUrl = "https://coinicons-api.vercel.app/api/icon/"

    const val NORMAL = "Normal"
    const val LOCAL_CURRENCY = "Local"
    const val DELAY = 5000L // 5 seconds
    const val LONG_DELAY = 300000L // 5 minutes
    val DAY_RANGE: IntRange = 0..5
    val HOUR_RANGE: IntRange = 9..16
}