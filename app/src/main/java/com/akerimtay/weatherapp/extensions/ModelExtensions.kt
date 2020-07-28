package com.akerimtay.weatherapp.extensions

import com.akerimtay.weatherapp.data.model.UnitSystem
import java.util.*

fun Locale.toUnitSystem() =
    when (country.toUpperCase(this)) {
        "US", "GB", "MM", "LR" -> UnitSystem.IMPERIAL.name
        else -> UnitSystem.METRIC.name
    }