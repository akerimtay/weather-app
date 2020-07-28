package com.akerimtay.weatherapp.data.provider

import android.content.Context
import com.akerimtay.weatherapp.currentCityName

class AppProviderImpl(context: Context) : AppProvider {
    companion object {
        private const val PREF_NAME = "APP_PREF_NAME"
        private const val KEY_CURRENT_CITY_NAME = "KEY_CURRENT_CITY_NAME"
    }

    private val sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

    override fun getCurrentCityName(): String? {
        return sharedPreferences.getString(KEY_CURRENT_CITY_NAME, currentCityName)
    }

    override fun setCurrentCityName(value: String?) {
        val editor = sharedPreferences.edit()
        editor.putString(KEY_CURRENT_CITY_NAME, value)
        editor.apply()
    }
}