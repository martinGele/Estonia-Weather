package com.martin.weatherestonia.view


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.martin.weatherestonia.R
import com.martin.weatherestonia.databinding.FragmentDetialScreenCurrentBinding

/**
 * A simple [Fragment] subclass.
 */
class DetialScreenCurrentFragment : Fragment() {

    lateinit var dataBinding: FragmentDetialScreenCurrentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        dataBinding =
            DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_detial_screen_current,
                container,
                false
            )
        return dataBinding.root
    }


}
