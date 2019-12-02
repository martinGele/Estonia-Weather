package com.martin.weatherestonia.viewmodel

import android.annotation.SuppressLint
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.martin.weatherestonia.database.AppDatabase
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
import javax.inject.Inject


class MainScreenViewModel(application: Application) : AndroidViewModel(application) {


    val weather by lazy { MutableLiveData<WeatherFourDays>() }
    val currentWeather by lazy { MutableLiveData<WeatherCurrent>() }
    val loadError by lazy { MutableLiveData<Boolean>() }
    val loading by lazy { MutableLiveData<Boolean>() }


    val date = AppDatabase.getInstance(application)


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
                    override fun onSuccess(list: WeatherFourDays) {


                        Observable.fromCallable {
                            date?.userDao()?.insertAllCurrent(list)


                        }.subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe()



                        loadError.value = false
                        weather.value = list
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

                        Observable.fromCallable {
                            with(date){
                                this?.userDao()?.insertAllObservation(current)

                            }

                        }.subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe()



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

}