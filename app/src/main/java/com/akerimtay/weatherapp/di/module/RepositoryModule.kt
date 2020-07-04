package com.akerimtay.weatherapp.di.module

import com.akerimtay.weatherapp.di.scope.DataScope
import com.akerimtay.weatherapp.repository.WeatherRepository
import com.akerimtay.weatherapp.repository.WeatherRepositoryImpl
import dagger.Binds
import dagger.Module

@Module
abstract class RepositoryModule {
    @Binds
    @DataScope
    abstract fun bindWeatherRepository(weatherRepositoryImpl: WeatherRepositoryImpl): WeatherRepository
}