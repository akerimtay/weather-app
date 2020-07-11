package com.akerimtay.weatherapp.utils

import android.content.Context
import android.text.format.DateUtils
import com.akerimtay.weatherapp.R
import java.text.SimpleDateFormat
import java.util.*

object DateUtils {
    @JvmStatic
    fun getTime(context: Context, date: Date?): String {
        if (date == null) return ""
        return SimpleDateFormat("HH:mm", LocaleUtil.getLocale(context.resources)).format(date)
    }

    @JvmStatic
    fun getLastUpdateTime(context: Context, value: Date?): String? {
        if (value == null) return ""
        val lastUpdated = context.getString(R.string.last_updated_with_colon)
        val date = when {
            DateUtils.isToday(value.time) -> context.getString(R.string.today)
            isYesterday(value) -> context.getString(R.string.yesterday)
            isThisYear(value) -> SimpleDateFormat("dd MMMM", LocaleUtil.getLocale(context.resources)).format(value)
            else -> SimpleDateFormat("dd MMMM yyyy", LocaleUtil.getLocale(context.resources)).format(value)
        }
        val time = SimpleDateFormat("HH:mm", LocaleUtil.getLocale(context.resources)).format(value)
        return "$lastUpdated $date, $time"
    }

    private fun isYesterday(date: Date): Boolean {
        val calendar = Calendar.getInstance()
        calendar.time = date
        calendar.add(Calendar.DATE, 1)
        return DateUtils.isToday(calendar.timeInMillis)
    }

    private fun isThisYear(date: Date): Boolean {
        val calendar = Calendar.getInstance()
        calendar.time = date
        return calendar.get(Calendar.YEAR) == Calendar.getInstance().get(Calendar.YEAR)
    }
}