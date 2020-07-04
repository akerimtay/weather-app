package com.akerimtay.weatherapp.di.component

import android.content.Context
import com.akerimtay.weatherapp.App
import com.akerimtay.weatherapp.di.module.ApplicationModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [(ApplicationModule::class)])
interface ApplicationComponent {
    fun context(): Context

    fun inject(app: App)

    fun plus(): NetworkComponent
}