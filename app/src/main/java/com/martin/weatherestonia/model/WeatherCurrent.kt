package com.martin.weatherestonia.model

import android.annotation.SuppressLint
import android.os.Parcel
import com.google.gson.annotations.SerializedName
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.martin.weatherestonia.database.Convert
import com.martin.weatherestonia.database.ConvertObservation

@Entity(tableName = "currentWeather")
@TypeConverters(ConvertObservation::class)
data class WeatherCurrent(
    @PrimaryKey
    val key:Int?=1,
    @SerializedName("timestamp")
    val timestamp: String = "",
    @SerializedName("observations")
    val observations: List<Observation> = listOf()
):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readValue(Int::class.java.classLoader) as? Int,
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