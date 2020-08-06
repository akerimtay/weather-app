package com.akerimtay.weatherapp.di.module

import android.app.Application
import android.content.Context
import com.akerimtay.weatherapp.data.provider.AppProvider
import com.akerimtay.weatherapp.data.provider.AppProviderImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule(private val application: Application) {
    @Provides
    @Singleton
    fun provideApplication(): Application = application

    @Provides
    @Singleton
    fun provideApplicationContext(): Context = application

    @Provides
    @Singleton
    fun provideAppProvider(): AppProvider = AppProviderImpl(application)
}