package com.example.geocoderactivity

import android.location.Geocoder
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import java.util.Locale

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val addressInput = findViewById<EditText>(R.id.addressText)
        val geoButton = findViewById<Button>(R.id.geoButton)
        val reverseGeoButton = findViewById<Button>(R.id.reverseGeoButton)
        val outputText = findViewById<TextView>(R.id.outputText)
        val geocoder = Geocoder(this, Locale.getDefault())

        geoButton.setOnClickListener {
            val address = addressInput.text.toString()
            if (!address.isNullOrEmpty()) {
                val location = geocoder.getFromLocationName(address, 1)
                if (!location.isNullOrEmpty()) {
                    val lat = location[0].latitude
                    val long = location[0].longitude
                    outputText.setText("Latitude: $lat\nLongitude: $long")
                } else {
                    outputText.setText("No location found")
                }
            } else {
                Toast.makeText(this, "Enter an address", Toast.LENGTH_SHORT).show()
            }
        }

        reverseGeoButton.setOnClickListener {
            try {
                val lat = 28.6139 //Eg: New Delhi
                val long = 77.2090
                val addresses = geocoder.getFromLocation(lat, long, 1)
                if (!addresses.isNullOrEmpty()) {
                    val address = addresses[0].getAddressLine(0)
                    outputText.text = "Address: $address"
                } else {
                    outputText.setText("No address found")
                }
            } catch (e: Exception) {
                e.printStackTrace()
                outputText.text = "Error: ${e.message}"
            }
        }
    }
}
