package com.martin.weatherestonia.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class Night(
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
    val places: Any? = Any(),
    @SerializedName("winds")
    val winds: Any? = Any()
):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readString(),
        TODO("sea"),
        TODO("peipsi"),
        TODO("places"),
        TODO("winds")
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(phenomenon)
        tempmin?.let { parcel.writeDouble(it) }
        tempmax?.let { parcel.writeDouble(it) }
        parcel.writeString(text)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Night> {
        override fun createFromParcel(parcel: Parcel): Night {
            return Night(parcel)
        }

        override fun newArray(size: Int): Array<Night?> {
            return arrayOfNulls(size)
        }
    }
}