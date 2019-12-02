package com.martin.weatherestonia.model


import android.media.AudioRecord.MetricsConstants.SOURCE
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.google.gson.annotations.SerializedName
import com.martin.weatherestonia.database.Convert
import java.lang.annotation.ElementType
import java.lang.annotation.RetentionPolicy

@Entity(tableName = "day")
@TypeConverters(Convert::class)
data class WeatherFourDays(
    @PrimaryKey
    var key: Int?=1,
    @SerializedName("forecasts")
    var forecasts: List<Forecast?> = listOf()
)
