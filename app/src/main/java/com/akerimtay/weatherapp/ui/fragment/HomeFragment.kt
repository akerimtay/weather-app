package com.akerimtay.weatherapp.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.akerimtay.weatherapp.R
import com.akerimtay.weatherapp.databinding.FragmentHomeBinding
import com.akerimtay.weatherapp.utils.showConnectionErrorToast
import com.akerimtay.weatherapp.viewmodel.HomeViewModel
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private val viewModel: HomeViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        swipeRefresh.setOnRefreshListener { updateUI(view) }
    }

    private fun updateUI(view: View) {
        viewModel.currentWeather.value?.let {
            viewModel.getCurrentWeatherByLocation(it.location.latitude, it.location.longitude, "Metric")
        } ?: showConnectionErrorToast(view.context)
    }
}