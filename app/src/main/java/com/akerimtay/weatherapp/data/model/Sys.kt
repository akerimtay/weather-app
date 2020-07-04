package com.akerimtay.weatherapp.data.model

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
@JsonClass(generateAdapter = true)
data class Sys(
    @Json(name = "country") val countryCode: String,
    @Json(name = "sunrise") val sunriseTime: Date,
    @Json(name = "sunset") val sunsetTime: Date
) : Parcelable