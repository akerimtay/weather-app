package com.akerimtay.weatherapp.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
data class Sys(
    @SerializedName("country") val countryCode: String,
    @SerializedName("sunrise") val sunriseTime: Date,
    @SerializedName("sunset") val sunsetTime: Date
) : Parcelable