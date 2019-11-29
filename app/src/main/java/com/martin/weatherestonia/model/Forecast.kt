package com.martin.weatherestonia.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class Forecast(
    @SerializedName("date")
    val date: String? = "",
    @SerializedName("day")
    val day: Day = Day(),
    @SerializedName("night")
    val night: Night? = Night()
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        TODO("day"),
        parcel.readParcelable(Night::class.java.classLoader)
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(date)
        parcel.writeParcelable(night, flags)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Forecast> {
        override fun createFromParcel(parcel: Parcel): Forecast {
            return Forecast(parcel)
        }

        override fun newArray(size: Int): Array<Forecast?> {
            return arrayOfNulls(size)
        }
    }
}