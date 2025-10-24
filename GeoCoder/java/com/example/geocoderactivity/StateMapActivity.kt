package com.example.geocoderactivity

import android.location.Geocoder
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.Locale

class StateMapActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var map: GoogleMap
    private lateinit var geocoder: Geocoder
    private var marker: Marker? = null

    private val states = listOf(
        "Maharashtra",
        "Karnataka",
        "Tamil Nadu",
        "Gujarat",
        "West Bengal",
        "Uttar Pradesh",
        "Kerala",
        "Punjab",
        "Rajasthan",
        "Assam"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_state_map)

        // Initialize Geocoder
        geocoder = Geocoder(this, Locale.getDefault())

        // ListView setup
        val stateListView = findViewById<ListView>(R.id.stateListView)
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, states)
        stateListView.adapter = adapter

        // Map setup
        val mapFragment = supportFragmentManager.findFragmentById(R.id.mapFragment) as SupportMapFragment
        mapFragment.getMapAsync(this)

        // List click listener
        stateListView.setOnItemClickListener { _, _, position, _ ->
            if (!::map.isInitialized) {
                Toast.makeText(this, "Map not ready yet!", Toast.LENGTH_SHORT).show()
                return@setOnItemClickListener
            }
            val stateName = states[position]
            moveMarkerToState(stateName)
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap
        val india = LatLng(20.5937, 78.9629)
        marker = map.addMarker(MarkerOptions().position(india).title("India"))
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(india, 5f))
    }

    private fun moveMarkerToState(state: String) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val addresses = geocoder.getFromLocationName("$state, India", 1)
                withContext(Dispatchers.Main) {
                    if (!addresses.isNullOrEmpty()) {
                        val location = addresses[0]
                        val latLng = LatLng(location.latitude, location.longitude)

                        Log.d("GeoCoder", "$state -> ${latLng.latitude}, ${latLng.longitude}")

                        marker?.remove()
                        marker = map.addMarker(MarkerOptions().position(latLng).title(state))
                        map.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 6f))
                    } else {
                        Toast.makeText(
                            this@StateMapActivity,
                            "Could not find location for $state",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
                withContext(Dispatchers.Main) {
                    Toast.makeText(
                        this@StateMapActivity,
                        "Geocoder failed: ${e.localizedMessage}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }
}
