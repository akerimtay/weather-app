package com.akerimtay.weatherapp.network

import com.akerimtay.weatherapp.model.CurrentWeather
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
    @GET("/data/2.5/weather")
    fun getCurrentWeatherByCityName(
        @Query("q") cityName: String,
        @Query("units") unit: String,
        @Query("lang") language: String?
    ): Single<CurrentWeather>

    @GET("/data/2.5/weather")
    fun getCurrentWeatherByLocation(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double,
        @Query("units") unit: String,
        @Query("lang") language: String?
    ): Single<CurrentWeather>
}