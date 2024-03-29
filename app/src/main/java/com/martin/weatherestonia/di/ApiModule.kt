package com.martin.weatherestonia.di

import com.martin.weatherestonia.network.WeatherApi
import com.martin.weatherestonia.network.WeatherApiService
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


@Module
open class ApiModule {
    private val URL: String = "https://weather.aw.ee/api/estonia/"


    @Provides
    fun provideOkHttpClient(interceptor: Interceptor) : OkHttpClient =
        OkHttpClient().newBuilder().addInterceptor(interceptor).build()


    @Provides
    fun provideWeatherApi(): WeatherApi {
        return Retrofit.Builder()
            .baseUrl(URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(WeatherApi::class.java)
    }


    @Provides
    open fun provideWeatherApiService(): WeatherApiService {
        return WeatherApiService()
    }


}