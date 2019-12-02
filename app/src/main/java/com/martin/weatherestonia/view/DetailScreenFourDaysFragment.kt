package com.martin.weatherestonia.view


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.martin.weatherestonia.R
import com.martin.weatherestonia.adapter.CitiesAdapter
import com.martin.weatherestonia.databinding.FragmentDetailScreenBinding
import com.martin.weatherestonia.model.Forecast
import kotlinx.android.synthetic.main.fragment_detail_screen.*

/**
 * A simple [Fragment] subclass.
 */
class DetailScreenFourDaysFragment : Fragment() {

    var weather: Forecast? = null

    private lateinit var dataBinding: FragmentDetailScreenBinding

    private val listAdapterCities = CitiesAdapter(arrayListOf())


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        dataBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_detail_screen, container, false)
        return dataBinding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let { weather = DetailScreenFourDaysFragmentArgs.fromBundle(it).forecast }
        dataBinding.weather = weather

        dataBinding.weather?.day?.places?.let { listAdapterCities.updateCitiesList(it) }

        recycleViewCities.apply {
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = listAdapterCities

        }


    }


}
