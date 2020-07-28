package com.akerimtay.weatherapp.data.repository

import com.akerimtay.weatherapp.data.model.CurrentWeather
import com.akerimtay.weatherapp.data.repository.datastore.WeatherDatabaseStore
import com.akerimtay.weatherapp.data.repository.datastore.WeatherNetworkStore
import io.reactivex.Single
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val networkStore: WeatherNetworkStore,
    private val databaseStore: WeatherDatabaseStore
) : WeatherRepository {

    override fun getCurrentWeatherByCityName(cityName: String): Single<CurrentWeather> {
        return networkStore.getCurrentWeatherByCityName(cityName)
            .flatMap {
                databaseStore.deleteAll()
                    .andThen(databaseStore.insert(it))
                    .andThen(databaseStore.getCurrentWeather())
            }
    }

    override fun getCurrentWeatherByLocation(latitude: Double, longitude: Double): Single<CurrentWeather> {
        return networkStore.getCurrentWeatherByLocation(latitude, longitude)
            .flatMap {
                databaseStore.deleteAll()
                    .andThen(databaseStore.insert(it))
                    .andThen(databaseStore.getCurrentWeather())
            }
    }

    override fun getCurrentWeatherLocal() = databaseStore.getCurrentWeather()
}