package com.akerimtay.weatherapp.data.provider

import android.content.Context

class AppProviderImpl(context: Context) : AppProvider {
    companion object {
        private const val PREF_NAME = "APP_PREF_NAME"
        private const val KEY_AUTO_LOCATE = "KEY_AUTO_LOCATE"
    }

    private val sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

    override fun getAutoLocate() = sharedPreferences.getBoolean(KEY_AUTO_LOCATE, true)

    override fun setAutoLocate(value: Boolean) {
        val editor = sharedPreferences.edit()
        editor.putBoolean(KEY_AUTO_LOCATE, value)
        editor.apply()
    }
}