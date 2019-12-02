package com.martin.weatherestonia.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class Day(
    @SerializedName("phenomenon")
    val phenomenon: String? = "",
    @SerializedName("tempmin")
    val tempmin: Double? = 0.0,
    @SerializedName("tempmax")
    val tempmax: Double? = 0.0,
    @SerializedName("text")
    val text: String? = "",
    @SerializedName("sea")
    val sea: Any? = Any(),
    @SerializedName("peipsi")
    val peipsi: Any? = Any(),
    @SerializedName("places")
    val places: List<Places>? = listOf(),
    @SerializedName("winds")
    val winds: Any? = Any()
):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readValue(Double::class.java.classLoader) as? Double,
        parcel.readValue(Double::class.java.classLoader) as? Double,
        parcel.readString(),
        TODO("sea"),
        TODO("peipsi"),
        parcel.createTypedArrayList(Places),
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
