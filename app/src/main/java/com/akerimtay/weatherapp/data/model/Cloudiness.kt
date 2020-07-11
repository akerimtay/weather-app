package com.akerimtay.weatherapp.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Cloudiness(@SerializedName("all") val value: Double) : Parcelable