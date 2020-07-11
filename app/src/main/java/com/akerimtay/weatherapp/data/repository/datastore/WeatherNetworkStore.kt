package com.akerimtay.weatherapp.data.repository.datastore

import android.content.Context
import com.akerimtay.weatherapp.data.model.CurrentWeather
import com.akerimtay.weatherapp.network.WeatherApi
import com.akerimtay.weatherapp.utils.LocaleUtil
import io.reactivex.Single
import javax.inject.Inject

class WeatherNetworkStore @Inject constructor(
    private val context: Context,
    private val weatherApi: WeatherApi
) {
    fun getCurrentWeatherByCityName(cityName: String, unit: String): Single<CurrentWeather> {
        val locale = LocaleUtil.getLocale(context.resources)
        val language = locale.language
        return weatherApi.getCurrentWeatherByCityName(cityName, unit, language)
    }

    fun getCurrentWeatherByLocation(latitude: Double, longitude: Double, unit: String): Single<CurrentWeather> {
        val locale = LocaleUtil.getLocale(context.resources)
        val language = locale.language
        return weatherApi.getCurrentWeatherByLocation(latitude, longitude, unit, language)
    }
}