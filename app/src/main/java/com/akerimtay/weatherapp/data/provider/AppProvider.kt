package com.akerimtay.weatherapp.data.provider

interface AppProvider {
    fun getAutoLocate(): Boolean

    fun setAutoLocate(value: Boolean)
}