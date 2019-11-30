package com.martin.weatherestonia

import com.martin.weatherestonia.di.ApiModule
import com.martin.weatherestonia.network.WeatherApiService

class ApiModuleTest(val mockService: WeatherApiService) : ApiModule() {

    override fun provideWeatherApiService(): WeatherApiService {
        return mockService

    }
}