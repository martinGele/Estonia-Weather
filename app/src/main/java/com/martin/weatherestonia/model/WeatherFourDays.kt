package com.martin.weatherestonia.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class WeatherFourDays(
    @SerializedName("forecasts")
    val forecasts: List<Forecast> = listOf()
) : Parcelable {
    constructor(parcel: Parcel) : this(parcel.createTypedArrayList(Forecast)!!) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeTypedList(forecasts)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<WeatherFourDays> {
        override fun createFromParcel(parcel: Parcel): WeatherFourDays {
            return WeatherFourDays(parcel)
        }

        override fun newArray(size: Int): Array<WeatherFourDays?> {
            return arrayOfNulls(size)
        }
    }
}