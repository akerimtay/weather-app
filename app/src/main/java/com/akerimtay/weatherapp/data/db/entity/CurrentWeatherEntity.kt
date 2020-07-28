package com.akerimtay.weatherapp.data.db.entity

import androidx.room.*
import com.akerimtay.weatherapp.data.db.mapping.Converters
import com.akerimtay.weatherapp.data.model.*
import java.util.*

@Entity(tableName = "current_weather")
@TypeConverters(Converters::class)
data class CurrentWeatherEntity(
    @PrimaryKey @ColumnInfo(name = "id") val id: Long,
    @Embedded(prefix = "location_") val location: Location,
    @ColumnInfo(name = "weathers") val weather: List<Weather>,
    @Embedded(prefix = "main_") val main: Main,
    @Embedded(prefix = "wind_") val wind: Wind,
    @Embedded(prefix = "cloudiness_") val cloudiness: Cloudiness,
    @ColumnInfo(name = "date") val date: Date,
    @Embedded(prefix = "sys_") val sys: Sys,
    @ColumnInfo(name = "city_name") val cityName: String
) {
    constructor(model: CurrentWeather) : this(
        0, model.location, model.weather, model.main,
        model.wind, model.cloudiness, model.date, model.sys, model.cityName
    )
}