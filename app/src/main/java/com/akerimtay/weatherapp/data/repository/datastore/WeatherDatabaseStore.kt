package com.akerimtay.weatherapp.data.repository.datastore

import com.akerimtay.weatherapp.data.db.dao.CurrentWeatherDao
import com.akerimtay.weatherapp.data.db.entity.CurrentWeatherEntity
import com.akerimtay.weatherapp.data.model.CurrentWeather
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import javax.inject.Inject

class WeatherDatabaseStore @Inject constructor(private val weatherDao: CurrentWeatherDao) {
    fun insert(currentWeather: CurrentWeather): Completable {
        return Completable.defer {
            weatherDao.insert(CurrentWeatherEntity(currentWeather))
            return@defer Completable.complete()
        }
    }

    fun getCurrentWeatherFlowable(): Flowable<CurrentWeather> {
        return weatherDao.getCurrentWeatherFlowable().map { CurrentWeather(it) }
    }

    fun getCurrentWeatherSingle(): Single<CurrentWeather> {
        return weatherDao.getCurrentWeatherSingle().map { CurrentWeather(it) }
    }

    fun getWeathersFlowable(): Flowable<List<CurrentWeather>> {
        return weatherDao.getWeathersFlowable()
            .flatMap { list ->
                return@flatMap Flowable.fromIterable(list)
                    .map { entity -> return@map CurrentWeather(entity) }
                    .toList()
                    .toFlowable()
            }
    }

    fun getWeathersSingle(): Single<List<CurrentWeather>> {
        return weatherDao.getWeathersSingle()
            .flattenAsObservable { weathers -> return@flattenAsObservable weathers }
            .map { entity -> return@map CurrentWeather(entity) }
            .toList()
    }

    fun delete(cityName: String): Completable {
        return Completable.defer {
            weatherDao.delete(cityName)
            return@defer Completable.complete()
        }
    }

    fun deleteAll(): Completable {
        return Completable.defer {
            weatherDao.deleteAll()
            return@defer Completable.complete()
        }
    }
}