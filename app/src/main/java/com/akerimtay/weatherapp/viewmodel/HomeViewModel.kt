package com.akerimtay.weatherapp.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.MutableLiveData
import com.akerimtay.weatherapp.App
import com.akerimtay.weatherapp.data.model.CurrentWeather
import com.akerimtay.weatherapp.data.repository.WeatherRepository
import com.akerimtay.weatherapp.defaultCurrentCityName
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class HomeViewModel(application: Application) : BaseViewModel(application) {

    @Inject
    lateinit var weatherRepository: WeatherRepository

    val viewState = MutableLiveData<ViewState>()
    val currentWeather: LiveData<CurrentWeather>

    init {
        (application as App).getDataComponent().inject(this)

        currentWeather = LiveDataReactiveStreams.fromPublisher(
            weatherRepository.getCurrentWeather()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
        )
    }

    fun getCurrentWeatherByLocation(latitude: Double, longitude: Double) {
        viewState.value = ViewState.Loading
        addToDisposables(
            weatherRepository.loadWeatherByLocation(latitude, longitude)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    viewState.value = ViewState.Success
                }, {
                    viewState.value = ViewState.Error
                    it.printStackTrace()
                })
        )
    }

    fun loadCurrentWeather(cityName: String) {
        viewState.value = ViewState.Loading
        addToDisposables(
            weatherRepository.loadWeatherByCityName(cityName)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    viewState.value = ViewState.Success
                }, {
                    viewState.value = ViewState.Error
                    it.printStackTrace()
                })
        )
    }

    fun updateWeather() {
        viewState.value = ViewState.Loading
        addToDisposables(
            weatherRepository.updateWeather()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    viewState.value = ViewState.Success
                }, {
                    viewState.value = ViewState.Error
                    loadCurrentWeather(defaultCurrentCityName)
                    it.printStackTrace()
                })
        )
    }

}