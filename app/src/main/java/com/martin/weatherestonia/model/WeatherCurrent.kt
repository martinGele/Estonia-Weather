package com.martin.weatherestonia.model

import android.annotation.SuppressLint
import android.os.Parcel
import com.google.gson.annotations.SerializedName
import android.os.Parcelable

data class WeatherCurrent(
    @SerializedName("timestamp")
    val timestamp: String = "",
    @SerializedName("observations")
    val observations: List<Observation> = listOf()
):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.createTypedArrayList(Observation)!!
    ) {
    }

    override fun writeToParcel(p0: Parcel?, p1: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun describeContents(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    companion object CREATOR : Parcelable.Creator<WeatherCurrent> {
        override fun createFromParcel(parcel: Parcel): WeatherCurrent {
            return WeatherCurrent(parcel)
        }

        override fun newArray(size: Int): Array<WeatherCurrent?> {
            return arrayOfNulls(size)
        }
    }
}