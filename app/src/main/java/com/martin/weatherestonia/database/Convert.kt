package com.martin.weatherestonia.database

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.martin.weatherestonia.model.Forecast
import com.martin.weatherestonia.model.Observation
import com.martin.weatherestonia.model.WeatherFourDays
import java.util.*

class Convert {

    private val gson = Gson()
    @TypeConverter
    fun stringToList(data: String?): List<Forecast> {
        if (data == null) {
            return Collections.emptyList()
        }

        val listType = object : TypeToken<List<Forecast>>() {

        }.type

        return gson.fromJson<List<Forecast>>(data, listType)
    }

    @TypeConverter
    fun listToString(someObjects: List<Forecast>): String {
        return gson.toJson(someObjects)
    }



}

class ConvertObservation{


    private val gson = Gson()
    @TypeConverter
    fun stringToList(data: String?): List<Observation> {
        if (data == null) {
            return Collections.emptyList()
        }

        val listType = object : TypeToken<List<Observation>>() {

        }.type

        return gson.fromJson<List<Observation>>(data, listType)
    }

    @TypeConverter
    fun listToString(someObjects: List<Observation>): String {
        return gson.toJson(someObjects)
    }

}


