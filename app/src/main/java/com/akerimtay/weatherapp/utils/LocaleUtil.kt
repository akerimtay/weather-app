package com.akerimtay.weatherapp.utils

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.Configuration
import android.content.res.Resources
import android.os.Build
import android.preference.PreferenceManager
import java.util.*

class LocaleUtil {

    companion object {
        private val LANGUAGE_KEY = "CHOOSE_LANGUAGE"

        fun setLocale(c: Context): Context {
            val savedLanguage = getLanguage(c)
            return savedLanguage?.let { updateResources(c, it) } ?: c
        }

        fun setNewLocale(c: Context, language: String): Context {
            persistLanguage(c, language)
            return updateResources(c, language)
        }

        fun setNewLocale(c: Context, newLocale: Locale): Context {
            persistLanguage(c, newLocale.toString())
            return updateResources(c, newLocale)
        }

        fun getLanguage(c: Context): String? {
            val prefs = PreferenceManager.getDefaultSharedPreferences(c)
            val currentLocale = getLocale(c.resources)
            return if (!prefs.contains(LANGUAGE_KEY)) {
                null
            } else {
                prefs.getString(LANGUAGE_KEY, currentLocale.toString())
            }
        }

        @SuppressLint("ApplySharedPref")
        private fun persistLanguage(c: Context, language: String) {
            val prefs = PreferenceManager.getDefaultSharedPreferences(c)
            prefs.edit().putString(LANGUAGE_KEY, language).commit()
        }

        private fun updateResources(context: Context, language: String): Context {
            val locale = Locale(language)
            Locale.setDefault(locale)
            return updateResources(context, locale)
        }

        @Suppress("DEPRECATION")
        private fun updateResources(context: Context, locale: Locale): Context {
            var myContext = context
            val res = myContext.resources
            val config = Configuration(res.configuration)
            config.setLocale(locale)
            myContext = myContext.createConfigurationContext(config)
            res.updateConfiguration(config, res.displayMetrics)
            return myContext
        }

        fun getLocale(res: Resources): Locale {
            val config = res.configuration
            return if (Build.VERSION.SDK_INT >= 24) config.locales.get(0) else config.locale
        }

        fun getSavedLocale(c: Context): Locale {
            val savedLanguage = getLanguage(c)
            return if (savedLanguage == null) getLocale(c.resources) else Locale(getLanguage(c))
        }
    }
}