package com.akerimtay.weatherapp.data.db.mapping

import androidx.room.TypeConverter
import com.akerimtay.weatherapp.data.model.Weather
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.*

class Converters {
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return if (value == null) null else Date(value)
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time
    }

    @TypeConverter
    fun stringToListWeather(data: String?): List<Weather>? {
        if (data == null) return emptyList()

        val listType = object : TypeToken<List<Weather>>() {}.type
        return Gson().fromJson(data, listType)
    }

    @TypeConverter
    fun listWeatherToString(data: List<Weather>?): String {
        return Gson().toJson(data)
    }
}