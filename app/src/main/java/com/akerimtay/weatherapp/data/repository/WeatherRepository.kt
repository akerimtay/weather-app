package com.akerimtay.weatherapp.data.repository

import com.akerimtay.weatherapp.data.model.CurrentWeather
import io.reactivex.Completable
import io.reactivex.Flowable

interface WeatherRepository {
    fun loadCurrentWeatherByCityName(cityName: String): Completable

    fun loadCurrentWeatherByLocation(latitude: Double, longitude: Double): Completable

    fun getCurrentWeather(): Flowable<CurrentWeather>

    fun getCities(): Flowable<List<CurrentWeather>>

    fun deleteCurrentWeather(cityName: String): Completable
}