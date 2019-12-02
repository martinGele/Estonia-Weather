package com.martin.weatherestonia.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.martin.weatherestonia.model.WeatherCurrent
import com.martin.weatherestonia.model.WeatherFourDays
import io.reactivex.Single


@Dao
interface WeatherDao {

    @Query("SELECT * FROM forecast")
    fun getForecast(): Single<WeatherFourDays>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllForecast(users: WeatherFourDays)



    @Query("SELECT * FROM current")
    fun getCurrentWeather(): Single<WeatherCurrent>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllCurrent(currentWeather: WeatherCurrent)
}