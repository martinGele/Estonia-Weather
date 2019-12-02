package com.martin.weatherestonia.model


import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class WeatherFourDays(
    @SerializedName("forecasts")
    val forecasts: List<Forecast?> = listOf()
)
