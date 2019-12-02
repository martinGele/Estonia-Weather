package com.martin.weatherestonia.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class Places(
    @SerializedName("name")
    val name: String? = "",
    @SerializedName("phenomenon")
    val phenomenon: String? = "",
    @SerializedName("tempmin")
    val tempmin: Double? = 0.0,
    @SerializedName("tempmax")
    val tempmax: Double? = 0.0
    ):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readValue(Double::class.java.classLoader) as? Double,
        parcel.readValue(Double::class.java.classLoader) as? Double
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(phenomenon)
        parcel.writeValue(tempmin)
        parcel.writeValue(tempmax)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Places> {
        override fun createFromParcel(parcel: Parcel): Places {
            return Places(parcel)
        }

        override fun newArray(size: Int): Array<Places?> {
            return arrayOfNulls(size)
        }
    }
}