package com.akerimtay.weatherapp.data.repository

import com.akerimtay.weatherapp.data.repository.datastore.WeatherDatabaseStore
import com.akerimtay.weatherapp.data.repository.datastore.WeatherNetworkStore
import io.reactivex.Completable
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val networkStore: WeatherNetworkStore,
    private val databaseStore: WeatherDatabaseStore
) : WeatherRepository {
    override fun loadWeatherByCityName(cityName: String): Completable {
        return Completable.defer {
            networkStore.getCurrentWeatherByCityName(cityName)
                .flatMapCompletable {
                    return@flatMapCompletable databaseStore.insert(it)
                }
        }
    }

    override fun loadWeatherByLocation(latitude: Double, longitude: Double): Completable {
        return Completable.defer {
            networkStore.getCurrentWeatherByLocation(latitude, longitude)
                .flatMapCompletable {
                    return@flatMapCompletable databaseStore.insert(it)
                }
        }
    }

    override fun updateWeathers(): Completable {
        return databaseStore.getWeathersSingle()
            .flattenAsObservable { weathers -> return@flattenAsObservable weathers }
            .flatMapCompletable { entity ->
                networkStore.getCurrentWeatherByCityName(entity.cityName)
                    .flatMapCompletable { weather -> databaseStore.insert(weather) }
            }
    }

    override fun getCurrentWeather() = databaseStore.getCurrentWeather()

    override fun getWeathers() = databaseStore.getWeathersFlowable()

    override fun deleteWeather(cityName: String) = databaseStore.delete(cityName)
}