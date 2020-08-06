package com.akerimtay.weatherapp.data.model

import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Parcelable
import com.akerimtay.weatherapp.R
import com.akerimtay.weatherapp.data.db.entity.CurrentWeatherEntity
import com.akerimtay.weatherapp.utils.LocaleUtil
import com.akerimtay.weatherapp.utils.weatherIconHighQ
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
) : Parcelable {
    constructor(entity: CurrentWeatherEntity) : this(
        entity.location, entity.weather, entity.main,
        entity.wind, entity.cloudiness, entity.date, entity.sys, entity.cityName
    )

    fun getImage(context: Context): Drawable? {
        return context.getDrawable(weatherIconHighQ(weather.first().icon))
    }

    fun getCity(context: Context): String {
        return "$cityName, ${sys.countryCode.toUpperCase(LocaleUtil.getLocale(context.resources))}"
    }

    fun getMinMaxFeelsTemp(context: Context): String {
        val minTemp = main.minTemperature.toInt()
        val maxTemp = main.maxTemperature.toInt()
        val feelsLikeInt = main.feelsLike.toInt()
        val feelsLikeString = context.getString(R.string.feels_like)
        return "$minTemp°/$maxTemp° $feelsLikeString $feelsLikeInt°"
    }

    fun getWeatherDescription() = weather.first().description

    fun getTemp() = "${main.temperature.toInt()}°"

    fun getWindSpeed() = "${wind.speed} м/с"

    fun getPressure() = "${main.pressure.toInt()} Па"

    fun getHumidity() = "${main.humidity.toInt()} %"

    fun getCloudinessValue() = "${cloudiness.value} %"

    fun getDescriptionAndTemp() = "${getWeatherDescription()} ${getTemp()}"
}