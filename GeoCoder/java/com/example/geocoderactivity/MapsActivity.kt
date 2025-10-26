package com.example.geocoderactivity

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.widget.AutocompleteSupportFragment
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private var sourceLatLng: LatLng? = null
    private var destinationLatLng: LatLng? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)

        // ✅ Initialize Places API safely
        if (!Places.isInitialized()) {
            Places.initialize(applicationContext, apiKey)
        }

        // ✅ Setup Map Fragment
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        // ✅ Setup Autocomplete Fragment
        val autocompleteFragment = supportFragmentManager
            .findFragmentById(R.id.autocomplete_fragment) as AutocompleteSupportFragment

        autocompleteFragment.setPlaceFields(
            listOf(Place.Field.ID, Place.Field.NAME, Place.Field.LAT_LNG)
        )

        autocompleteFragment.setOnPlaceSelectedListener(object : PlaceSelectionListener {
            override fun onPlaceSelected(place: Place) {
                mMap.clear()
                val selectedLocation = place.latLng!!
                mMap.addMarker(MarkerOptions().position(selectedLocation).title(place.name))
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(selectedLocation, 14f))

                // ✅ First click → Source; second click → Destination
                if (sourceLatLng == null) {
                    sourceLatLng = selectedLocation
                } else {
                    destinationLatLng = selectedLocation
                    drawRoute(sourceLatLng!!, destinationLatLng!!)
                }
            }

            override fun onError(status: com.google.android.gms.common.api.Status) {
                // Optional: Log or show a Toast
                println("Autocomplete Error: ${status.statusMessage}")
            }
        })
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        mMap.uiSettings.isZoomControlsEnabled = true
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(LatLng(20.5937, 78.9629), 4f)) // India
    }

    private fun drawRoute(source: LatLng, destination: LatLng) {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://maps.googleapis.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(DirectionsApiService::class.java)

        service.getDirections(
            "${source.latitude},${source.longitude}",
            "${destination.latitude},${destination.longitude}",
            apiKey
        ).enqueue(object : Callback<DirectionsResponse> {
            override fun onResponse(
                call: Call<DirectionsResponse>,
                response: Response<DirectionsResponse>
            ) {
                val points = response.body()?.routes?.firstOrNull()?.overview_polyline?.decode()
                if (points != null) {
                    val polylineOptions = PolylineOptions()
                        .addAll(points)
                        .color(Color.BLUE)
                        .width(8f)
                    mMap.addPolyline(polylineOptions)
                    mMap.addMarker(MarkerOptions().position(source).title("Source"))
                    mMap.addMarker(MarkerOptions().position(destination).title("Destination"))
                    mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(source, 10f))
                }
            }

            override fun onFailure(call: Call<DirectionsResponse>, t: Throwable) {
                t.printStackTrace()
            }
        })
    }
}

/** Retrofit API interface **/
interface DirectionsApiService {
    @GET("maps/api/directions/json")
    fun getDirections(
        @Query("origin") origin: String,
        @Query("destination") destination: String,
        @Query("key") key: String
    ): Call<DirectionsResponse>
}

/** Data Models for Directions API **/
data class DirectionsResponse(val routes: List<Route>)
data class Route(val overview_polyline: OverviewPolyline)
data class OverviewPolyline(val points: String)

/** Polyline decoding extension **/
fun OverviewPolyline.decode(): List<LatLng> {
    val poly = ArrayList<LatLng>()
    var index = 0
    val len = points.length
    var lat = 0
    var lng = 0

    while (index < len) {
        var b: Int
        var shift = 0
        var result = 0
        do {
            b = points[index++].code - 63
            result = result or ((b and 0x1f) shl shift)
            shift += 5
        } while (b >= 0x20)
        val dlat = if (result and 1 != 0) (result shr 1).inv() else result shr 1
        lat += dlat

        shift = 0
        result = 0
        do {
            b = points[index++].code - 63
            result = result or ((b and 0x1f) shl shift)
            shift += 5
        } while (b >= 0x20)
        val dlng = if (result and 1 != 0) (result shr 1).inv() else result shr 1
        lng += dlng

        val latLng = LatLng(lat / 1E5, lng / 1E5)
        poly.add(latLng)
    }
    return poly
}
