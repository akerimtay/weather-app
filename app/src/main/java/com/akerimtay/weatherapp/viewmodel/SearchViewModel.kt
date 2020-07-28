package com.akerimtay.weatherapp.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.MutableLiveData
import com.akerimtay.weatherapp.App
import com.akerimtay.weatherapp.data.model.CurrentWeather
import com.akerimtay.weatherapp.data.repository.WeatherRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class SearchViewModel(application: Application) : BaseViewModel(application) {
    @Inject
    lateinit var weatherRepository: WeatherRepository

    val viewState = MutableLiveData<ViewState>()
    val cities: LiveData<List<CurrentWeather>>

    init {
        (application as App).getDataComponent().inject(this)

        cities = LiveDataReactiveStreams.fromPublisher(
            weatherRepository.getCities()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
        )
    }

    fun loadCurrentWeather(cityName: String) {
        viewState.value = ViewState.Loading
        addToDisposables(
            weatherRepository.loadCurrentWeatherByCityName(cityName)
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

    fun delete(cityName: String) {
        addToDisposables(
            weatherRepository.deleteCurrentWeather(cityName)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                }, {
                    it.printStackTrace()
                })
        )
    }
}