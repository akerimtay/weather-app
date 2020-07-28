package com.akerimtay.weatherapp.di.module

import android.app.Application
import com.akerimtay.weatherapp.data.db.AppDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RoomModule(application: Application) {
    private val appDatabase = AppDatabase.getInstance(application)

    @Provides
    @Singleton
    fun provideCurrentWeatherDao() = appDatabase.currentWeatherDao()
}