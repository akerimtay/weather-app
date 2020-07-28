package com.akerimtay.weatherapp.data.repository.datastore

import android.content.Context
import com.akerimtay.weatherapp.data.model.CurrentWeather
import com.akerimtay.weatherapp.data.network.WeatherApi
import com.akerimtay.weatherapp.extensions.toUnitSystem
import com.akerimtay.weatherapp.utils.LocaleUtil
import io.reactivex.Single
import javax.inject.Inject

class WeatherNetworkStore @Inject constructor(
    private val context: Context,
    private val weatherApi: WeatherApi
) {
    fun getCurrentWeatherByCityName(cityName: String): Single<CurrentWeather> {
        val locale = LocaleUtil.getLocale(context.resources)
        val language = locale.language
        val unit = locale.toUnitSystem()
        return weatherApi.getCurrentWeatherByCityName(cityName, unit, language)
    }

    fun getCurrentWeatherByLocation(latitude: Double, longitude: Double): Single<CurrentWeather> {
        val locale = LocaleUtil.getLocale(context.resources)
        val language = locale.language
        val unit = locale.toUnitSystem()
        return weatherApi.getCurrentWeatherByLocation(latitude, longitude, unit, language)
    }
}