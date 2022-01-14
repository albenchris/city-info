package com.example.cityinfo

import android.content.DialogInterface
import android.content.pm.PackageManager
import android.location.Geocoder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.SearchView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import java.util.*
import java.util.jar.Manifest

class MainActivity : AppCompatActivity() {

    lateinit var searchCityName : SearchView
    lateinit var tvCityName : TextView
    lateinit var tvCityLat : TextView
    lateinit var tvCityLong : TextView

    // ======== FOR TESTING ONLY =========
//    lateinit var rawCodeData : TextView
    // ===================================

    val LOCATION_COARSE_PERMISSION_CODE : Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvCityName = findViewById(R.id.textViewCityName)
        tvCityLat = findViewById(R.id.textViewLat)
        tvCityLong = findViewById(R.id.textViewLong)

        // ======== FOR TESTING ONLY =========
//        rawCodeData = findViewById(R.id.rawCodeData)
        // ===================================

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

        if (ActivityCompat.checkSelfPermission(this,
                android.Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED
        ) {
            requestPermission(
                android.Manifest.permission.ACCESS_COARSE_LOCATION,
                LOCATION_COARSE_PERMISSION_CODE,
                "Using location will provide closest searched location in case of duplicates"
            )
        }
    }

    private fun requestPermission(
        requestedPermission: String,
        PERMISSION_CODE: Int,
        message: String
    ) {
        if (ActivityCompat
                .shouldShowRequestPermissionRationale(this,
                    requestedPermission
                )
        ) {
            AlertDialog.Builder(this)
                .setTitle("Permission needed")
                .setMessage(message)
                .setPositiveButton("OK", DialogInterface.OnClickListener { dialog, which ->
                    ActivityCompat.requestPermissions(this,
                        Array<String>(1) { requestedPermission },
                        PERMISSION_CODE
                    )
                })
                .setNegativeButton("Cancel", DialogInterface.OnClickListener { dialog, which ->
                    dialog.dismiss()
                })
                .create()
                .show()
        } else {
            ActivityCompat.requestPermissions(this,
                Array<String>(1) { requestedPermission },
                PERMISSION_CODE
            )
        }
    }

    private fun getLocationInfo(cityName: String?) {
        val geocoder = Geocoder(this, Locale.getDefault())
        val location = geocoder.getFromLocationName(
            cityName,
            1
        )[0]

        // ========== TESTING ONLY ============
        // using this to read location data
//        rawCodeData.text = location.toString()
        // ====================================

        tvCityName.text = location.getAddressLine(0)
        tvCityLat.text = location.latitude.toString()
        tvCityLong.text = location.longitude.toString()
    }
}