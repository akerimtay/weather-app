package com.akerimtay.weatherapp.ui.adapter.diffutil

import androidx.recyclerview.widget.DiffUtil
import com.akerimtay.weatherapp.data.model.CurrentWeather

class CurrentWeatherCallback(
    private val oldItems: List<CurrentWeather>,
    private val newItems: List<CurrentWeather>
) : DiffUtil.Callback() {
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldItems[oldItemPosition].cityName == newItems[newItemPosition].cityName
    }

    override fun getOldListSize(): Int {
        return oldItems.size
    }

    override fun getNewListSize(): Int {
        return newItems.size
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldItems[oldItemPosition] == newItems[newItemPosition]
    }
}