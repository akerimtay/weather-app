package com.akerimtay.weatherapp.repository

import com.akerimtay.weatherapp.model.CurrentWeather
import com.akerimtay.weatherapp.repository.datastore.WeatherNetworkStore
import io.reactivex.Single
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(private val networkStore: WeatherNetworkStore) :
    WeatherRepository {

    override fun getCurrentWeatherByCityName(cityName: String, unit: String): Single<CurrentWeather> {
        return networkStore.getCurrentWeatherByCityName(cityName, unit)
    }

    override fun getCurrentWeatherByLocation(latitude: Double, longitude: Double, unit: String): Single<CurrentWeather> {
        return networkStore.getCurrentWeatherByLocation(latitude, longitude, unit)
    }
}