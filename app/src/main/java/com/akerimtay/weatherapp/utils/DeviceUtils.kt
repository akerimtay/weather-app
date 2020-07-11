package com.akerimtay.weatherapp.utils

fun isConnectedNetwork(): Boolean {
    val runtime = Runtime.getRuntime()

    try {
        val ipProcess = runtime.exec("/system/bin/ping -c 1 8.8.8.8")
        val exitValue = ipProcess.waitFor()
        return exitValue == 0
    } catch (e: Exception) {
        e.printStackTrace()
    }

    return false
}