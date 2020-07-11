package com.akerimtay.weatherapp.utils

import android.content.Context
import android.widget.Toast
import com.akerimtay.weatherapp.R

fun showSimpleToast(context: Context, message: String?) {
    Toast.makeText(context, message, Toast.LENGTH_LONG).show()
}

fun showSimpleToast(context: Context, message: Int) {
    Toast.makeText(context, message, Toast.LENGTH_LONG).show()
}

fun showConnectionErrorToast(context: Context) {
    Toast.makeText(context, R.string.connection_error, Toast.LENGTH_LONG).show()
}