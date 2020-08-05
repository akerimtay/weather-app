package com.akerimtay.weatherapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.akerimtay.weatherapp.R
import com.akerimtay.weatherapp.data.model.CurrentWeather
import com.akerimtay.weatherapp.databinding.ItemCityBinding
import com.akerimtay.weatherapp.extensions.setOnSingleClickListener
import com.akerimtay.weatherapp.ui.adapter.diffutil.CurrentWeatherCallback

class CitiesAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val items = mutableListOf<CurrentWeather>()
    private var eventsListener: CityEventsListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: ItemCityBinding =
            DataBindingUtil.inflate(inflater, R.layout.item_city, parent, false)
        return CityHolder(binding)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = items[position]
        if (holder is CityHolder) {
            holder.binding.apply {
                weather = item
                executePendingBindings()
                root.setOnSingleClickListener { eventsListener?.onItemClick(item) }
                imgDelete.setOnSingleClickListener { eventsListener?.onDeleteClick(item) }
            }
        }
    }

    fun updateItems(newItems: List<CurrentWeather>) {
        val diffResult = DiffUtil.calculateDiff(CurrentWeatherCallback(items, newItems))
        items.clear()
        items.addAll(newItems)
        diffResult.dispatchUpdatesTo(this)
    }

    fun setOnEventsListener(listener: CityEventsListener?) {
        eventsListener = listener
    }

    class CityHolder(val binding: ItemCityBinding) : RecyclerView.ViewHolder(binding.root)

    interface CityEventsListener {
        fun onItemClick(currentWeather: CurrentWeather)

        fun onDeleteClick(currentWeather: CurrentWeather)
    }
}