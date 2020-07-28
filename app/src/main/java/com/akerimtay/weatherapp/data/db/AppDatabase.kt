package com.akerimtay.weatherapp.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.akerimtay.weatherapp.data.db.dao.CurrentWeatherDao
import com.akerimtay.weatherapp.data.db.entity.CurrentWeatherEntity
import com.akerimtay.weatherapp.data.db.mapping.Converters

@Database(
    entities = [CurrentWeatherEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun currentWeatherDao(): CurrentWeatherDao

    companion object {
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            if (INSTANCE == null) {
                INSTANCE = buildDatabase(context)
            }
            return INSTANCE as AppDatabase
        }

        private fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(context, AppDatabase::class.java, "weather_app.db")
                .build()
        }
    }
}