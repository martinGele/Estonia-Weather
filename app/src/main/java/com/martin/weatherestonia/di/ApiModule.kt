package com.martin.weatherestonia.di

import com.martin.weatherestonia.network.WeatherApi
import com.martin.weatherestonia.network.WeatherApiService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


@Module
class ApiModule {
    private val URL: String = "https://weather.aw.ee/api/estonia/"


    @Provides
    fun provideAnimalAPi(): WeatherApi {
        return Retrofit.Builder()
            .baseUrl(URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(WeatherApi::class.java)
    }


    @Provides
    fun provideAnimalApiService(): WeatherApiService {
        return WeatherApiService()
    }


}