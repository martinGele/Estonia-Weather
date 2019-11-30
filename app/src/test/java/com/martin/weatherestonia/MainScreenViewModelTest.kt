package com.martin.weatherestonia

import android.app.Application
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.martin.weatherestonia.di.AppModule
import com.martin.weatherestonia.di.DaggerViewModelComponent
import com.martin.weatherestonia.model.*
import com.martin.weatherestonia.network.WeatherApiService
import com.martin.weatherestonia.viewmodel.MainScreenViewModel
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.internal.schedulers.ExecutorScheduler
import io.reactivex.plugins.RxJavaPlugins
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import java.util.concurrent.Executor

class MainScreenViewModelTest {


    @get:Rule
    var rule = InstantTaskExecutorRule()


    @Mock
    lateinit var weatherApiService: WeatherApiService

    val application = Mockito.mock(Application::class.java)

    var mainScreenViewModel = MainScreenViewModel(application, true)



    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)

        DaggerViewModelComponent.builder()
            .appModule(AppModule(application))
            .apiModule(ApiModuleTest(weatherApiService))
            .build()
            .inject(mainScreenViewModel)
    }

    @Before
    fun setupRxSchedulers() {
        val imidiate = object : Scheduler() {
            override fun createWorker(): Worker {

                return ExecutorScheduler.ExecutorWorker(Executor {
                    it.run()
                }, true)
            }
        }
        RxJavaPlugins.setInitNewThreadSchedulerHandler { shceduler ->

            imidiate
        }
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { scheduler ->
            imidiate
        }

    }

    @Test
    fun getCurrentWeatherSuccess(){

        val observation= Observation("macko",
            null,null,null,
            null,null, null, null,
            "sas", "2333",null,
            null, null, null, null,
            null, null)



        val currentWeather=WeatherCurrent("87876785", listOf(observation))
        val testSingle = Single.just(currentWeather)

        Mockito.`when`(weatherApiService.getCurrentWeather()).thenReturn(testSingle)

        mainScreenViewModel.showCurrentWeather()
        Assert.assertEquals(null, mainScreenViewModel.weather.value)
        Assert.assertEquals(false, mainScreenViewModel.loadError.value)
        Assert.assertEquals(false, mainScreenViewModel.loading.value)


    }

    @Test
    fun getCurrentWeatherError(){

        val keySingle= Single.error<WeatherCurrent>(Throwable())
        Mockito.`when`(weatherApiService.getCurrentWeather()).thenReturn(keySingle)

        mainScreenViewModel.showCurrentWeather()

        Assert.assertEquals(null, mainScreenViewModel.weather.value)
        Assert.assertEquals(true, mainScreenViewModel.loadError.value)
        Assert.assertEquals(false, mainScreenViewModel.loading.value)


    }

    @Test
    fun getWeatherSuccess() {


        val day= Day("Something",null,
            null,null,null,null,"skopje",null)
        val night = Night("foddyWeather",null,
            null,null,null,null,null,null)
        val forecast= Forecast("122", day,night)
        val forecastList = listOf(forecast)
        val weather = WeatherFourDays(forecastList)
        val testSingle = Single.just(weather)

        Mockito.`when`(weatherApiService.getWeather()).thenReturn(testSingle)

        mainScreenViewModel.showFourDaysWeather()
        Assert.assertEquals(weather, mainScreenViewModel.weather.value)
        Assert.assertEquals(false, mainScreenViewModel.loadError.value)
        Assert.assertEquals(false, mainScreenViewModel.loading.value)
    }


    @Test
    fun getWeatherError(){

        val keySingle= Single.error<WeatherFourDays>(Throwable())
        Mockito.`when`(weatherApiService.getWeather()).thenReturn(keySingle)

        mainScreenViewModel.showFourDaysWeather()

        Assert.assertEquals(null, mainScreenViewModel.weather.value)
        Assert.assertEquals(true, mainScreenViewModel.loadError.value)
        Assert.assertEquals(false, mainScreenViewModel.loading.value)


    }
}