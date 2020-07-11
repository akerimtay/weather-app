package com.akerimtay.weatherapp.di.module

import com.akerimtay.weatherapp.data.repository.WeatherRepository
import com.akerimtay.weatherapp.data.repository.WeatherRepositoryImpl
import com.akerimtay.weatherapp.di.scope.DataScope
import dagger.Binds
import dagger.Module

@Module
abstract class RepositoryModule {
    @Binds
    @DataScope
    abstract fun bindWeatherRepository(weatherRepositoryImpl: WeatherRepositoryImpl): WeatherRepository
}