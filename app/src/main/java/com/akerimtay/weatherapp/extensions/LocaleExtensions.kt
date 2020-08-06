package com.akerimtay.weatherapp.extensions

import com.akerimtay.weatherapp.data.model.UnitSystem
import java.util.*

fun Locale.toUnitSystem() =
    when (country.toUpperCase(this)) {
        "US", "GB", "MM", "LR" -> UnitSystem.IMPERIAL.name
        else -> UnitSystem.METRIC.name
    }

fun Locale.toTemperatureUnit() =
    when (country.toUpperCase(this)) {
        "US", "GB", "MM", "LR" -> "F"
        else -> "C"
    }

fun Locale.toSpeedUnit() =
    when (country.toUpperCase(this)) {
        "US", "GB", "MM", "LR" -> "mph"
        else -> "м/с"
    }

fun Locale.toPressureUnit() =
    when (country.toUpperCase(this)) {
        "US", "GB", "MM", "LR" -> "Pa"
        else -> "Па"
    }