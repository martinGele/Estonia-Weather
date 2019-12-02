package com.martin.weatherestonia.view


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.martin.weatherestonia.R
import com.martin.weatherestonia.adapter.CitiesAdapter
import com.martin.weatherestonia.databinding.FragmentDetailScreenBinding
import com.martin.weatherestonia.model.Forecast

/**
 * A simple [Fragment] subclass.
 */
class DetailScreenFourDaysFragment : Fragment() {

    var weather: Forecast? = null

    private lateinit var dataBinding: FragmentDetailScreenBinding

    private val listAdapterCities = CitiesAdapter(arrayListOf())



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail_screen, container, false)
        return dataBinding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let { weather = DetailScreenFourDaysFragmentArgs.fromBundle(it).forecast }
        dataBinding.weather = weather


        Log.d("GEtttt", dataBinding.weather.toString())



        }






}
