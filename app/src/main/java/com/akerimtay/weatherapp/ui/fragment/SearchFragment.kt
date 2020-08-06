package com.akerimtay.weatherapp.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.akerimtay.weatherapp.R
import com.akerimtay.weatherapp.data.model.CurrentWeather
import com.akerimtay.weatherapp.databinding.FragmentSearchBinding
import com.akerimtay.weatherapp.extensions.setOnSingleClickListener
import com.akerimtay.weatherapp.ui.adapter.CitiesAdapter
import com.akerimtay.weatherapp.viewmodel.SearchViewModel
import kotlinx.android.synthetic.main.fragment_search.*

class SearchFragment : Fragment(), CitiesAdapter.CityEventsListener {
    private lateinit var binding: FragmentSearchBinding
    private val viewModel: SearchViewModel by viewModels()
    private lateinit var adapter: CitiesAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_search, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        swipeRefresh.isEnabled = false

        imgBack.setOnSingleClickListener { activity?.onBackPressed() }
        imgSearch.setOnSingleClickListener { search() }
        inputCityName.setOnEditorActionListener { _, _, _ ->
            search()
            return@setOnEditorActionListener true
        }

        adapter = CitiesAdapter()
        adapter.setOnEventsListener(this)
        recyclerView.adapter = adapter

        viewModel.weathers.observe(viewLifecycleOwner, Observer {
            adapter.updateItems(it)
        })

        viewModel.updateWeathers()
    }

    override fun onItemClick(currentWeather: CurrentWeather) {
        viewModel.loadWeatherByCityName(currentWeather.cityName)
        activity?.onBackPressed()
    }

    override fun onDeleteClick(currentWeather: CurrentWeather) {
        viewModel.delete(currentWeather.cityName)
    }

    private fun search() {
        val cityName = inputCityName.text.trim().toString()
        if (cityName.isNotEmpty()) {
            viewModel.searchByCityName(cityName)
        }
        inputCityName.setText("")
    }
}