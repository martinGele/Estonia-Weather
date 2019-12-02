package com.martin.weatherestonia.model


import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import com.google.gson.annotations.SerializedName

@Entity(tableName = "forecast")
data class WeatherFourDays(
    @SerializedName("forecasts")
    val forecasts: List<Forecast?> = listOf()
)
