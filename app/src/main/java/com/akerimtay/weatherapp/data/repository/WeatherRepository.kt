package com.akerimtay.weatherapp.data.repository

import com.akerimtay.weatherapp.data.model.CurrentWeather
import io.reactivex.Single

interface WeatherRepository {
    fun getCurrentWeatherByCityName(cityName: String, unit: String): Single<CurrentWeather>

    fun getCurrentWeatherByLocation(latitude: Double, longitude: Double, unit: String): Single<CurrentWeather>
}