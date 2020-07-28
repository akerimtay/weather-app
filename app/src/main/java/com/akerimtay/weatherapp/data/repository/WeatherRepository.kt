package com.akerimtay.weatherapp.data.repository

import com.akerimtay.weatherapp.data.model.CurrentWeather
import io.reactivex.Flowable

interface WeatherRepository {
    fun getCurrentWeatherByCityName(cityName: String): Flowable<CurrentWeather>

    fun getCurrentWeatherByLocation(latitude: Double, longitude: Double): Flowable<CurrentWeather>

    fun getCurrentWeatherLocal(): Flowable<CurrentWeather>
}