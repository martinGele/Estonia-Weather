package com.martin.weatherestonia.model


import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class Day(
    @SerializedName("peipsi")
    val peipsi: Any?,
    @SerializedName("phenomenon")
    val phenomenon: String?,
    @SerializedName("places")
    val places: List<Places>?,
    @SerializedName("sea")
    val sea: Any?,
    @SerializedName("tempmax")
    val tempmax: Double?,
    @SerializedName("tempmin")
    val tempmin: Double?,
    @SerializedName("text")
    val text: String?,
    @SerializedName("winds")
    val winds: Any?
):Parcelable {
    constructor(parcel: Parcel) : this(
        TODO("peipsi"),
        parcel.readString(),
        TODO("places"),
        TODO("sea"),
        parcel.readValue(Double::class.java.classLoader) as? Double,
        parcel.readValue(Double::class.java.classLoader) as? Double,
        parcel.readString(),
        TODO("winds")
    ) {
    }

    override fun writeToParcel(p0: Parcel?, p1: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun describeContents(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    companion object CREATOR : Parcelable.Creator<Day> {
        override fun createFromParcel(parcel: Parcel): Day {
            return Day(parcel)
        }

        override fun newArray(size: Int): Array<Day?> {
            return arrayOfNulls(size)
        }
    }
}