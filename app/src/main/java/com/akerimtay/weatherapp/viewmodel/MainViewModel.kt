package com.akerimtay.weatherapp.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.akerimtay.weatherapp.App
import com.akerimtay.weatherapp.data.model.CurrentWeather
import com.akerimtay.weatherapp.data.repository.WeatherRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MainViewModel(application: Application) : BaseViewModel(application) {
    @Inject
    lateinit var weatherRepository: WeatherRepository

    val viewState = MutableLiveData<ViewState>()
    val currentWeather = MutableLiveData<CurrentWeather>()

    init {
        (application as App).getDataComponent().inject(this)
        getCurrentWeatherByCityName("Moscow", "Metric")
    }

    fun getCurrentWeatherByCityName(cityName: String, unit: String) {
        viewState.value = ViewState.Loading
        addToDisposables(
            weatherRepository.getCurrentWeatherByCityName(cityName, unit)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    viewState.value = ViewState.Success
                    currentWeather.value = it
                }, {
                    viewState.value = ViewState.Error
                    it.printStackTrace()
                })
        )
    }

    fun getCurrentWeatherByLocation(latitude: Double, longitude: Double, unit: String) {
        viewState.value = ViewState.Loading
        addToDisposables(
            weatherRepository.getCurrentWeatherByLocation(latitude, longitude, unit)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    viewState.value = ViewState.Success
                    currentWeather.value = it
                }, {
                    viewState.value = ViewState.Error
                    it.printStackTrace()
                })
        )
    }
}