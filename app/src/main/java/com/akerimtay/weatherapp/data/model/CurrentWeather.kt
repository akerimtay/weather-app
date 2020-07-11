package com.akerimtay.weatherapp.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
data class CurrentWeather(
    @SerializedName("coord") val location: Location,
    @SerializedName("weather") val weather: List<Weather>,
    @SerializedName("main") val main: Main,
    @SerializedName("wind") val wind: Wind,
    @SerializedName("clouds") val cloudiness: Cloudiness,
    @SerializedName("dt") val date: Date,
    @SerializedName("sys") val sys: Sys,
    @SerializedName("name") val cityName: String
) : Parcelable