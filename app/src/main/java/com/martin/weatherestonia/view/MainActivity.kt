package com.martin.weatherestonia.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.martin.weatherestonia.R

class MainActivity : AppCompatActivity() {
    private lateinit var navControler: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        navControler= Navigation.findNavController(this,R.id.fragment)
        NavigationUI.setupActionBarWithNavController(this,navControler)
    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navControler,null)
    }
}
