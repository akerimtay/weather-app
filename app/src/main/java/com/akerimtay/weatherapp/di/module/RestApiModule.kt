package com.akerimtay.weatherapp.di.module

import com.akerimtay.weatherapp.di.scope.NetworkScope
import com.akerimtay.weatherapp.network.WeatherApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class RestApiModule{
    @Provides
    @NetworkScope
    fun provideWeatherApi(retrofit: Retrofit): WeatherApi = retrofit.create(WeatherApi::class.java)
}