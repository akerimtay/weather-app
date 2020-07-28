package com.akerimtay.weatherapp.data.repository.datastore

import com.akerimtay.weatherapp.data.db.dao.CurrentWeatherDao
import com.akerimtay.weatherapp.data.db.entity.CurrentWeatherEntity
import com.akerimtay.weatherapp.data.model.CurrentWeather
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class WeatherDatabaseStore @Inject constructor(private val currentWeatherDao: CurrentWeatherDao) {
    fun insert(currentWeather: CurrentWeather): Completable {
        return Completable.defer {
            currentWeatherDao.insert(CurrentWeatherEntity(currentWeather))
            return@defer Completable.complete()
        }
    }

    fun getCurrentWeather(): Single<CurrentWeather> {
        return currentWeatherDao.getCurrentWeather().map { CurrentWeather(it) }
    }

    fun deleteAll(): Completable {
        return Completable.defer {
            currentWeatherDao.deleteAll()
            return@defer Completable.complete()
        }
    }
}