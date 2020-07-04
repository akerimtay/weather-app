package com.akerimtay.weatherapp

import android.app.Application
import com.akerimtay.weatherapp.di.component.ApplicationComponent
import com.akerimtay.weatherapp.di.component.DaggerApplicationComponent
import com.akerimtay.weatherapp.di.component.DataComponent
import com.akerimtay.weatherapp.di.component.NetworkComponent
import com.akerimtay.weatherapp.di.module.ApplicationModule

class App : Application() {
    companion object {
        private lateinit var applicationComponent: ApplicationComponent
    }

    private var networkComponent: NetworkComponent? = null
    private var dataComponent: DataComponent? = null

    override fun onCreate() {
        super.onCreate()
        applicationComponent = DaggerApplicationComponent.builder()
            .applicationModule(ApplicationModule(this))
            .build()
        applicationComponent.inject(this)
    }

    fun getApplicationComponent() = applicationComponent

    fun getNetworkComponent(): NetworkComponent {
        return networkComponent ?: applicationComponent.plus()
    }

    fun getDataComponent(): DataComponent {
        return dataComponent ?: getNetworkComponent().plus()
    }
}