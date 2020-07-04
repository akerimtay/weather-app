package com.akerimtay.weatherapp.data.model

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
@JsonClass(generateAdapter = true)
data class CurrentWeather(
    @Json(name = "coord") val location: Location,
    @Json(name = "weather") val weather: List<Weather>,
    @Json(name = "main") val main: Main,
    @Json(name = "wind") val wind: Wind,
    @Json(name = "clouds") val cloudiness: Cloudiness,
    @Json(name = "dt") val date: Date,
    @Json(name = "sys") val sys: Sys,
    @Json(name = "name") val cityName: String
) : Parcelable