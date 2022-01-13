package com.example.cityinfo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.SearchView
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    lateinit var tvCityName : TextView
    lateinit var tvCityLat : TextView
    lateinit var tvCityLong : TextView

    lateinit var searchCityName : SearchView
//    lateinit var buttonSearch : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvCityName = findViewById(R.id.textViewCityName)
        tvCityLat = findViewById(R.id.textViewLat)
        tvCityLong = findViewById(R.id.textViewLong)

        searchCityName = findViewById(R.id.searchCity)
        searchCityName.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                getLocationInfo(query)
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
//                getLocationInfo(newText)
                return false
            }
        })

//        buttonSearch = findViewById(R.id.buttonSearch)
//        buttonSearch.setOnClickListener {
//            getLocationInfo()
//        }

    }

    private fun getLocationInfo(cityName: String?) {
        tvCityName.text = cityName

    }
}