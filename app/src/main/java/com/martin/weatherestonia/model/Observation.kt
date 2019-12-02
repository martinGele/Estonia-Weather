package com.martin.weatherestonia.model

import android.annotation.SuppressLint
import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName



@SuppressLint("ParcelCreator")
data class Observation(
    @SerializedName("name")
    val name: String? = "",
    @SerializedName("wmocode")
    val wmocode: String? = "",
    @SerializedName("longitude")
    val longitude: String? = "",
    @SerializedName("latitude")
    val latitude: String? = "",
    @SerializedName("phenomenon")
    val phenomenon: String? = "",
    @SerializedName("visibility")
    val visibility: String? = "",
    @SerializedName("precipitations")
    val precipitations: String? = "",
    @SerializedName("airpressure")
    val airpressure: String? = "",
    @SerializedName("relativehumidity")
    val relativehumidity: String? = "",
    @SerializedName("airtemperature")
    val airtemperature: String? = "",
    @SerializedName("winddirection")
    val winddirection: String? = "",
    @SerializedName("windspeed")
    val windspeed: String? = "",
    @SerializedName("windspeedmax")
    val windspeedmax: String? = "",
    @SerializedName("waterlevel")
    val waterlevel: String? = "",
    @SerializedName("waterlevel_eh2000")
    val waterlevelEh2000: String? = "",
    @SerializedName("watertemperature")
    val watertemperature: String? = "",
    @SerializedName("uvindex")
    val uvindex: String? = ""
):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(wmocode)
        parcel.writeString(longitude)
        parcel.writeString(latitude)
        parcel.writeString(phenomenon)
        parcel.writeString(visibility)
        parcel.writeString(precipitations)
        parcel.writeString(airpressure)
        parcel.writeString(relativehumidity)
        parcel.writeString(airtemperature)
        parcel.writeString(winddirection)
        parcel.writeString(windspeed)
        parcel.writeString(windspeedmax)
        parcel.writeString(waterlevel)
        parcel.writeString(waterlevelEh2000)
        parcel.writeString(watertemperature)
        parcel.writeString(uvindex)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Observation> {
        override fun createFromParcel(parcel: Parcel): Observation {
            return Observation(parcel)
        }

        override fun newArray(size: Int): Array<Observation?> {
            return arrayOfNulls(size)
        }
    }
}