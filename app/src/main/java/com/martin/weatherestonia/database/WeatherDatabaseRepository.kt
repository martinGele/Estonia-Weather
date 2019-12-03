package com.martin.weatherestonia.database

import androidx.lifecycle.LiveData
import com.martin.weatherestonia.model.WeatherCurrent
import com.martin.weatherestonia.model.WeatherFourDays
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class WeatherDatabaseRepository(private val weatherDao: WeatherDao) {


    val getCurrentWeather: LiveData<WeatherFourDays> = weatherDao.getCurrentWeather()

    val getForecastWeather: LiveData<WeatherCurrent> = weatherDao.getCurrentObservation()


     fun insertCurrent(weather: WeatherFourDays) {
        GlobalScope.launch(Dispatchers.IO) {
            weatherDao.insertAllCurrent(weather)
        }
    }

     fun insertForecast(weather: WeatherCurrent) {
        GlobalScope.launch(Dispatchers.IO){
            weatherDao.insertAllObservation(weather)

        }
    }

}