package com.martin.weatherestonia.network

import com.martin.weatherestonia.di.DaggerApiComponent
import com.martin.weatherestonia.model.WeatherCurrent
import com.martin.weatherestonia.model.WeatherFourDays
import io.reactivex.Single
import javax.inject.Inject

class WeatherApiService {


    @Inject
    lateinit var api: WeatherApi


    init {
        DaggerApiComponent.create().inject(this)
    }

    fun getWeather(): Single<WeatherFourDays> {

        return api.getWeather()
    }

    fun getCurrentWeather(): Single<WeatherCurrent> {

        return api.getCurrentWeather()
    }

}