package com.martin.weatherestonia.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.martin.weatherestonia.R
import com.martin.weatherestonia.databinding.ItemListBinding
import com.martin.weatherestonia.model.Forecast

class WeatherAdapter(private val forecastList: ArrayList<Forecast>) :
    RecyclerView.Adapter<WeatherAdapter.WeatherViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        val view =
            DataBindingUtil.inflate<ItemListBinding>(inflater, R.layout.item_list, parent, false)

        return WeatherViewHolder(view)
    }

    override fun getItemCount(): Int {
        return forecastList.size
    }

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
    }

    fun updateAnimalList(forecasts: List<Forecast>) {
        forecastList.clear()
        forecastList.addAll(forecasts)
        notifyDataSetChanged()
    }

    class WeatherViewHolder(var view: ItemListBinding) : RecyclerView.ViewHolder(view.root)

}