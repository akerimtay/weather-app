package com.akerimtay.weatherapp.data.provider

interface AppProvider {
    fun getCurrentCityName(): String?

    fun setCurrentCityName(value: String?)
}