package com.martin.weatherestonia.view


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.martin.weatherestonia.R
import com.martin.weatherestonia.adapter.CurrentDayWeatherAdapter
import com.martin.weatherestonia.adapter.FourDaysFourcastAdapter
import com.martin.weatherestonia.database.AppDatabase
import com.martin.weatherestonia.model.Forecast
import com.martin.weatherestonia.model.WeatherCurrent
import com.martin.weatherestonia.model.WeatherFourDays
import com.martin.weatherestonia.utils.isConnected
import com.martin.weatherestonia.viewmodel.MainScreenViewModel
import kotlinx.android.synthetic.main.fragment_main_screen.*

/**
 * A simple [Fragment] subclass.
 */
class MainScreenFragment : Fragment() {

    private lateinit var viewModel: MainScreenViewModel
    private val listAdapterFOurDaysForecast = FourDaysFourcastAdapter(arrayListOf())
    private val listAdapterCurrentWeather = CurrentDayWeatherAdapter(arrayListOf())


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        return inflater.inflate(R.layout.fragment_main_screen, container, false)


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val data = context?.let { AppDatabase.getInstance(it) }


        viewModel = ViewModelProviders.of(this).get(MainScreenViewModel::class.java)




        if (context!!.isConnected) {
            viewModel.showFourDaysWeather()
            viewModel.showCurrentWeather()

            viewModel.weather.observe(this, weatherListDataObserver)
            viewModel.currentWeather.observe(this, currentWeatherDataObserver)
            viewModel.loading.observe(this, loadingLiveDataObserver)
            viewModel.loadError.observe(this, onErrorLiveDataObserver)

        } else {


            viewModel.currentWeatherDB.observe(this, currentWeatherDataObserverDB)
            viewModel.weatherDB.observe(this, weatherListDataObserverDB)
        }


        weatherForecastCurrent.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = listAdapterCurrentWeather

        }
        weatherForecastRecycler.apply {
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = listAdapterFOurDaysForecast
        }

        refresh_layout.setOnRefreshListener {
            if(context!!.isConnected) {
                weatherForecastRecycler.visibility = View.GONE
                weatherForecastCurrent.visibility = View.GONE
                listError.visibility = View.GONE
                loadingView.visibility = View.VISIBLE
                viewModel.showFourDaysWeather()
                viewModel.showCurrentWeather()

                refresh_layout.isRefreshing = false
            }else{
                refresh_layout.isRefreshing = false
                Toast.makeText(context,R.string.no_internet, Toast.LENGTH_LONG).show()

            }
        }

    }


    private val weatherListDataObserver = Observer<WeatherFourDays> { list ->

        list?.let {
            weatherForecastRecycler.visibility = View.VISIBLE
            listAdapterFOurDaysForecast.updateWeatherList(it.forecasts as List<Forecast>)
        }
    }

    private val currentWeatherDataObserver = Observer<WeatherCurrent> { current ->

        current?.let {
            weatherForecastCurrent.visibility = View.VISIBLE
            listAdapterCurrentWeather.updateCurrentWeather(it.observations)
        }
    }
    private val loadingLiveDataObserver = Observer<Boolean> { isLoading ->
        loadingView.visibility = if (isLoading) View.VISIBLE else View.GONE
        if (isLoading) {
            listError.visibility = View.GONE
            weatherForecastRecycler.visibility = View.GONE
        }
    }
    private val onErrorLiveDataObserver = Observer<Boolean> { error ->
        listError.visibility = if (error) View.VISIBLE else View.GONE
    }


    private val currentWeatherDataObserverDB = Observer<WeatherCurrent> { current ->

        current?.let {
            weatherForecastCurrent.visibility = View.VISIBLE
            listAdapterCurrentWeather.updateCurrentWeather(it.observations)
        }
    }

    private val weatherListDataObserverDB = Observer<WeatherFourDays> { list ->

        list?.let {
            weatherForecastRecycler.visibility = View.VISIBLE
            listAdapterFOurDaysForecast.updateWeatherList(it.forecasts as List<Forecast>)
        }
    }


}
