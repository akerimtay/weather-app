package com.akerimtay.weatherapp.extensions

import android.view.View
import com.akerimtay.weatherapp.ui.widget.OnSingleClickListener

fun View.setOnSingleClickListener(l: (View?) -> Unit) {
    setOnClickListener(object : OnSingleClickListener() {
        override fun onSingleClick(v: View) {
            l.invoke(v)
        }
    })
}

fun View.gone(shouldBeGone: Boolean) {
    visibility = if (shouldBeGone) {
        View.GONE
    } else {
        View.VISIBLE
    }
}