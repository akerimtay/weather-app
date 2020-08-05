package com.akerimtay.weatherapp.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.akerimtay.weatherapp.data.db.entity.CurrentWeatherEntity
import io.reactivex.Flowable

@Dao
interface CurrentWeatherDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(data: CurrentWeatherEntity)

    @Query("SELECT * FROM current_weather ORDER BY timestamp DESC")
    fun getCurrentWeather(): Flowable<CurrentWeatherEntity>

    @Query("SELECT * FROM current_weather")
    fun getWeathers(): Flowable<List<CurrentWeatherEntity>>

    @Query("DELETE FROM current_weather WHERE city_name = :cityName")
    fun delete(cityName: String)

    @Query("DELETE FROM current_weather")
    fun deleteAll()
}