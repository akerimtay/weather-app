package com.akerimtay.weatherapp.data.model

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class Location(
    @Json(name = "lon") val longitude: Double,
    @Json(name = "lat") val latitude: Double
) : Parcelable