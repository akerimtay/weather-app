package com.akerimtay.weatherapp.ui.widget

import android.os.SystemClock
import android.view.View

abstract class OnSingleClickListener : View.OnClickListener {
    companion object {
        private const val MIN_CLICK_INTERVAL: Long = 1000 //in millis
    }

    private var lastClickTime: Long = 0

    override fun onClick(v: View) {
        val currentTime = SystemClock.elapsedRealtime()
        if (currentTime - lastClickTime > MIN_CLICK_INTERVAL) {
            lastClickTime = currentTime
            onSingleClick(v)
        }
    }

    abstract fun onSingleClick(v: View)
}