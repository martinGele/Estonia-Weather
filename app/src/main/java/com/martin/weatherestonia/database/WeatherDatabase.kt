package com.martin.weatherestonia.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.martin.weatherestonia.model.WeatherCurrent
import com.martin.weatherestonia.model.WeatherFourDays


@Database(entities = arrayOf(WeatherFourDays::class, WeatherCurrent::class), version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): WeatherDao
}