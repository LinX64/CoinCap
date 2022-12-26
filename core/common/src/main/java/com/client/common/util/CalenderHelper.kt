package com.client.common.util

import java.util.*

class CalenderHelper {

    companion object {

        private fun setupCalendar(): Calendar {
            val calendar = Calendar.getInstance()
            calendar.timeZone = TimeZone.getTimeZone("Asia/Tehran")
            return calendar
        }

        private fun getIranTime(): HashMap<String, Int> {
            val calendar = setupCalendar()
            val day = calendar.get(Calendar.DAY_OF_WEEK)
            val hour = calendar.get(Calendar.HOUR_OF_DAY)
            val minute = calendar.get(Calendar.MINUTE)
            val second = calendar.get(Calendar.SECOND)
            return hashMapOf(
                "day" to day,
                "hour" to hour,
                "minute" to minute,
                "second" to second
            )
        }

        fun getDay(): Int = getIranTime()["day"] ?: 0

        fun getHour(): Int = getIranTime()["hour"] ?: 0

        fun getMinute(): Int = getIranTime()["minute"] ?: 0

        fun getSecond(): Int = getIranTime()["second"] ?: 0
    }
}