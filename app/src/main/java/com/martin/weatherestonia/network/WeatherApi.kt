package com.martin.weatherestonia.network

import com.martin.weatherestonia.model.WeatherFourDays
import io.reactivex.Single
import retrofit2.http.GET

interface WeatherApi {

    @GET("forecast")
    fun getWeather(): Single<WeatherFourDays>

}