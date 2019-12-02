package com.martin.weatherestonia.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.martin.weatherestonia.model.*
import io.reactivex.Flowable
import io.reactivex.Single


@Dao
interface WeatherDao {



    @Query("SELECT * FROM day")
    fun getCurrentWeather(): LiveData<WeatherFourDays>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllCurrent(currentWeather: WeatherFourDays)

    @Query("SELECT * FROM currentWeather")
    fun getCurrentObservation():LiveData<WeatherCurrent>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllObservation(currentWeather: WeatherCurrent)
}