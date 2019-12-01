package com.martin.weatherestonia.utils

import android.content.Context
import android.content.res.Resources
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.martin.weatherestonia.R
import java.text.SimpleDateFormat


fun getProgressDrawable(contex: Context): CircularProgressDrawable {

    return CircularProgressDrawable(contex).apply {
        strokeWidth = 6f
        centerRadius = 50f
        start()
    }
}

fun ImageView.loadImage(image: String, progressDrawable: CircularProgressDrawable) {

    val options = RequestOptions()
        .placeholder(progressDrawable)
        .error(R.mipmap.ic_launcher_round)


    Glide.with(context)
        .setDefaultRequestOptions(options)
        .load(image?.let { getIconResourceForWeatherCondition(it) })
        .into(this)

}


fun getIconResourceForWeatherCondition(weatherId: String): Int {
    if (weatherId == "Thunderstorm") {
        return R.drawable.ic_storm
    } else if (weatherId == "Light rain"
        || weatherId == "Moderate rain"
        || weatherId == "Heavy rain"
        || weatherId == "Light shower"
    ) {
        return R.drawable.ic_light_rain
    } else if (weatherId == "Moderate rain") {
        return R.drawable.ic_rain
    } else if (weatherId == "Snowstorm"
        || weatherId == "Drifting snow"
        || weatherId == "Heavy snow shower"
        || weatherId == "Heavy snowfall"
        || weatherId == "Light snow shower"
        || weatherId == "Moderate snowfall"
        || weatherId == "Light snowfall"
        || weatherId == "Moderate snow shower"
    ) {
        return R.drawable.ic_snow
    } else if (weatherId == "Fog") {
        return R.drawable.ic_fog

    } else if (weatherId == "Clear") {
        return R.drawable.ic_clear
    } else if (weatherId == "Few clouds") {
        return R.drawable.ic_light_clouds
    } else if (weatherId == "Cloudy with clear spells"
        || weatherId == "Cloudy"
        || weatherId == "Variable clouds"
    ) {
        return R.drawable.ic_cloudy
    }
    return -1
}

@BindingAdapter("android:imageUrl")
fun loadImage(view: ImageView, uri: String) {
    view.loadImage(uri, getProgressDrawable(view.context))

}

fun ImageView.loadImageToday(image: Int, progressDrawable: CircularProgressDrawable) {

    val options = RequestOptions()
        .placeholder(progressDrawable)
        .error(R.mipmap.ic_launcher_round)


    Glide.with(context)
        .setDefaultRequestOptions(options)
        .load(image?.let { getArtResourceForWeatherCondition(it) })
        .into(this)

}


fun getArtResourceForWeatherCondition(weatherId: Int): Int {
    if (weatherId in 200..232) {
        return R.drawable.art_storm
    } else if (weatherId in 300..321) {
        return R.drawable.art_light_rain
    } else if (weatherId in 500..504) {
        return R.drawable.art_rain
    } else if (weatherId == 511) {
        return R.drawable.art_snow
    } else if (weatherId in 520..531) {
        return R.drawable.art_rain
    } else if (weatherId in 600..622) {
        return R.drawable.art_rain
    } else if (weatherId in 701..761) {
        return R.drawable.art_fog
    } else if (weatherId == 761 || weatherId == 781) {
        return R.drawable.art_storm
    } else if (weatherId == 800) {
        return R.drawable.art_clear
    } else if (weatherId == 801) {
        return R.drawable.art_light_clouds
    } else if (weatherId in 802..804) {
        return R.drawable.art_clouds
    }
    return -1

}

@BindingAdapter("android:ImageToday")
fun loadImageToday(view: ImageView, uri: Int) {
    view.loadImageToday(uri, getProgressDrawable(view.context))

}

@BindingAdapter("android:showDay")
fun showDayOfWeek(view: TextView, date: String) {
    val dateParsed = SimpleDateFormat("yyyy-MM-dd").parse(date)
    view.text = SimpleDateFormat("EEEE").format(dateParsed)
}

