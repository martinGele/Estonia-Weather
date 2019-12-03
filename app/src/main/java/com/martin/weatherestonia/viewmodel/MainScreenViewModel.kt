package com.martin.weatherestonia.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.martin.weatherestonia.database.AppDatabase
import com.martin.weatherestonia.database.WeatherDatabaseRepository
import com.martin.weatherestonia.di.AppModule
import com.martin.weatherestonia.di.DaggerViewModelComponent
import com.martin.weatherestonia.model.WeatherCurrent
import com.martin.weatherestonia.model.WeatherFourDays
import com.martin.weatherestonia.network.WeatherApiService
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch
import javax.inject.Inject


class MainScreenViewModel(application: Application) : AndroidViewModel(application) {


    val weather by lazy { MutableLiveData<WeatherFourDays>() }
    val currentWeather by lazy { MutableLiveData<WeatherCurrent>() }
    val loadError by lazy { MutableLiveData<Boolean>() }
    val loading by lazy { MutableLiveData<Boolean>() }


    val weatherDB: LiveData<WeatherFourDays>
    val currentWeatherDB: LiveData<WeatherCurrent>


    private val repositoryDatabase: WeatherDatabaseRepository


    init {

        val dataBase = AppDatabase.getInstance(application)!!.weatherDao()
        repositoryDatabase = WeatherDatabaseRepository(dataBase)

        weatherDB = repositoryDatabase.getCurrentWeather
        currentWeatherDB = repositoryDatabase.getForecastWeather

    }


    private val disposable = CompositeDisposable()
    private var injected = false


    @Inject
    lateinit var apiService: WeatherApiService

    constructor(application: Application, test: Boolean = true) : this(application) {

        injected = true
    }

    fun inject() {

        if (!injected) {
            DaggerViewModelComponent.builder()
                .appModule(AppModule(getApplication()))
                .build()
                .inject(this)
        }
    }


    fun showFourDaysWeather() {

        inject()
        loading.value = true

        getWeather()


    }

    fun showCurrentWeather() {

        inject()
        loading.value = true
        getCurrentWeather()

    }




    private fun getWeather() {
        disposable.add(
            apiService.getWeather()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<WeatherFourDays>() {
                    override fun onSuccess(weatherFourDays: WeatherFourDays) {

                        insertFourDaysDB(weatherFourDays)

                        loadError.value = false
                        weather.value = weatherFourDays
                        loading.value = false

                    }

                    override fun onError(e: Throwable) {
                        e.printStackTrace()
                        loading.value = false
                        loadError.value = true
                        weather.value = null
                    }
                })
        )

    }

    private fun getCurrentWeather() {

        disposable.add(

            apiService.getCurrentWeather()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<WeatherCurrent>() {
                    override fun onSuccess(current: WeatherCurrent) {



                        insertCurrentDB(current)

                        loadError.value = false
                        currentWeather.value = current
                        loading.value = false

                    }

                    override fun onError(e: Throwable) {
                        e.printStackTrace()
                        loading.value = false
                        loadError.value = true
                        currentWeather.value = null
                    }

                })
        )
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }

    fun insertFourDaysDB(weather: WeatherFourDays) = viewModelScope.launch {
        repositoryDatabase.insertCurrent(weather)
    }


    fun insertCurrentDB(weather: WeatherCurrent)= viewModelScope.launch {
        repositoryDatabase.insertForecast(weather)
    }



}