package com.martin.weatherestonia.model

import android.annotation.SuppressLint
import android.os.Parcel
import com.google.gson.annotations.SerializedName
import android.os.Parcelable

@SuppressLint("ParcelCreator")
data class WeatherCurrent(
    @SerializedName("timestamp")
    val timestamp: String = "",
    @SerializedName("observations")
    val observations: List<Observation> = listOf()
):Parcelable {
    override fun writeToParcel(p0: Parcel?, p1: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun describeContents(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}