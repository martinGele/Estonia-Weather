package com.martin.weatherestonia.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.martin.weatherestonia.R
import com.martin.weatherestonia.databinding.ItemListCurrentDayBinding
import com.martin.weatherestonia.model.Observation

class CurrentDayWeatherAdapter(private val observationList: ArrayList<Observation>) :
    RecyclerView.Adapter<CurrentDayWeatherAdapter.ObservationViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ObservationViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view =
            DataBindingUtil.inflate<ItemListCurrentDayBinding>(
                inflater,
                R.layout.item_list_current_day,
                parent,
                false
            )

        return ObservationViewHolder(view)
    }

    override fun getItemCount(): Int {
        return observationList.size
    }

    override fun onBindViewHolder(holder: ObservationViewHolder, position: Int) {

        holder.view.weather = observationList[position]

    }

    fun updateCurrentWeather(forecasts: List<Observation>) {
        observationList.clear()
        observationList.addAll(forecasts)
        notifyDataSetChanged()
    }

    class ObservationViewHolder(var view: ItemListCurrentDayBinding) : RecyclerView.ViewHolder(view.root)


}