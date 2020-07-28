package com.akerimtay.weatherapp.data.repository

import com.akerimtay.weatherapp.data.model.CurrentWeather
import io.reactivex.Completable
import io.reactivex.Flowable

interface WeatherRepository {
    fun getCurrentWeatherByCityName(cityName: String): Completable

    fun getCurrentWeatherByLocation(latitude: Double, longitude: Double): Completable

    fun getCurrentWeatherLocal(): Flowable<CurrentWeather>

    fun getCurrentWeatherLocalAll(): Flowable<List<CurrentWeather>>

    fun deleteCurrentWeather(cityName: String): Completable
}