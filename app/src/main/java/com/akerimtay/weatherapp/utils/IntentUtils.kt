package com.akerimtay.weatherapp.utils

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.Settings

fun getSettingsIntent(context: Context): Intent? {
    val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
    val uri = Uri.fromParts("package", context.packageName, null)
    intent.data = uri
    return intent
}