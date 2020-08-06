package com.akerimtay.weatherapp.ui.binding

import android.view.View
import androidx.databinding.BindingAdapter
import com.akerimtay.weatherapp.extensions.gone

@BindingAdapter("gone")
fun bindGone(view: View, shouldBeGone: Boolean) {
    view.gone(shouldBeGone)
}