package com.akerimtay.weatherapp.di.component

import android.content.Context
import com.akerimtay.weatherapp.di.module.NetworkModule
import com.akerimtay.weatherapp.di.module.RestApiModule
import com.akerimtay.weatherapp.di.scope.NetworkScope
import dagger.Subcomponent
import retrofit2.Retrofit

@NetworkScope
@Subcomponent(modules = [(NetworkModule::class), (RestApiModule::class)])
interface NetworkComponent {
    fun context(): Context

    fun retrofit(): Retrofit

    fun plus(): DataComponent
}