package com.akerimtay.weatherapp.data.repository

import com.akerimtay.weatherapp.data.model.CurrentWeather
import io.reactivex.Completable
import io.reactivex.Flowable

interface WeatherRepository {

    fun loadWeatherByCityName(cityName: String): Completable

    fun loadWeatherByLocation(latitude: Double, longitude: Double): Completable

    fun getCurrentWeather(): Flowable<CurrentWeather>

    fun updateWeather(): Completable

    fun updateWeathers(): Completable

    fun getWeathers(): Flowable<List<CurrentWeather>>

    fun deleteWeather(cityName: String): Completable

}