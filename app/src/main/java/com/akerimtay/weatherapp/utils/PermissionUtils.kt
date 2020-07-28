package com.akerimtay.weatherapp.utils

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment

val locationPermissions = arrayOf(
    Manifest.permission.ACCESS_FINE_LOCATION,
    Manifest.permission.ACCESS_COARSE_LOCATION
)

fun verifyGrantResults(grantResults: IntArray): Boolean {
    grantResults.forEach {
        if (it == PackageManager.PERMISSION_DENIED) return false
    }
    return true
}

fun Fragment.isPermissionsGranted(context: Context, permissions: Array<String>): Boolean {
    return verifyGrantResults(permissions.map {
        ContextCompat.checkSelfPermission(context, it)
    }.toIntArray())
}

fun Fragment.shouldShowRequestPermissionRationale(permissions: Array<String>): Boolean {
    permissions.forEach {
        if (!shouldShowRequestPermissionRationale(it)) {
            return false
        }
    }
    return true
}