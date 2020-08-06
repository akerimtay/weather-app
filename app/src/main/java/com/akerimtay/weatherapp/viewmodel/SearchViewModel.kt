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

    val actionState = MutableLiveData<ActionState>()
    val weathers: LiveData<List<CurrentWeather>>

    init {
        (application as App).getDataComponent().inject(this)

        weathers = LiveDataReactiveStreams.fromPublisher(
            weatherRepository.getWeathers()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
        )
    }

    fun updateWeathers() {
        addToDisposables(
            weatherRepository.updateWeathers()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                }, {
                    it.printStackTrace()
                })
        )
    }

    fun loadCurrentWeather(cityName: String) {
        actionState.value = ActionState.Processing
        addToDisposables(
            weatherRepository.loadWeatherByCityName(cityName)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    actionState.value = ActionState.Successful
                }, {
                    actionState.value = ActionState.Failure
                    it.printStackTrace()
                })
        )
    }

    fun delete(cityName: String) {
        addToDisposables(
            weatherRepository.deleteWeather(cityName)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                }, {
                    it.printStackTrace()
                })
        )
    }
}