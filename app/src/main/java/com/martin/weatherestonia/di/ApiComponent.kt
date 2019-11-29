package com.martin.weatherestonia.di

import com.martin.weatherestonia.network.WeatherApiService
import dagger.Component

@Component(modules = [ApiModule::class])
interface ApiComponent {

    fun inject(service: WeatherApiService)
}