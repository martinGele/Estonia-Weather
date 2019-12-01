package com.martin.weatherestonia.view


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.martin.weatherestonia.R
import com.martin.weatherestonia.databinding.FragmentDetailScreenBinding

/**
 * A simple [Fragment] subclass.
 */
class DetailScreenFourDaysFragment : Fragment() {

    private lateinit var dataBinding: FragmentDetailScreenBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        dataBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_detail_screen, container, false)
        return dataBinding.root
    }


}
