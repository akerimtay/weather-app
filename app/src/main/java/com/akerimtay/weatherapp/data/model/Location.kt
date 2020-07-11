package com.akerimtay.weatherapp.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Location(
    @SerializedName("lon") val longitude: Double,
    @SerializedName("lat") val latitude: Double
) : Parcelable