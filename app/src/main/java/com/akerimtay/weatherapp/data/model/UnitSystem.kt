package com.akerimtay.weatherapp.data.model

import com.google.gson.annotations.SerializedName

enum class UnitSystem {
    @SerializedName("IMPERIAL")
    IMPERIAL,

    @SerializedName("METRIC")
    METRIC
}