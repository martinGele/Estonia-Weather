package com.martin.weatherestonia.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.martin.weatherestonia.R
import com.martin.weatherestonia.databinding.ItemListFourDaysForecastBinding
import com.martin.weatherestonia.model.Forecast
import com.martin.weatherestonia.view.MainScreenFragmentDirections
import kotlinx.android.synthetic.main.item_list_four_days_forecast.view.*

class FourDaysFourcastAdapter(private val forecastList: ArrayList<Forecast>) :
    RecyclerView.Adapter<FourDaysFourcastAdapter.WeatherViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        val view =
            DataBindingUtil.inflate<ItemListFourDaysForecastBinding>(inflater, R.layout.item_list_four_days_forecast, parent, false)

        return WeatherViewHolder(view)
    }

    override fun getItemCount(): Int {
        return forecastList.size
    }

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        holder.view.weather = forecastList[position]
        holder.itemView.itemListLayout.setOnClickListener {
            val action = MainScreenFragmentDirections.goToDetails(forecastList[position])

            Navigation.findNavController(holder.itemView).navigate(action)
        }
    }

    fun updateWeatherList(forecasts: List<Forecast>) {
        forecastList.clear()
        forecastList.addAll(forecasts)
        notifyDataSetChanged()
    }

    class WeatherViewHolder(var view: ItemListFourDaysForecastBinding) : RecyclerView.ViewHolder(view.root)

}