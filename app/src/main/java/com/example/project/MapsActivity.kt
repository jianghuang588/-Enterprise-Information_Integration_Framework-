package com.example.project
import android.content.Intent
import android.location.Address
import android.location.Geocoder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.example.project.databinding.ActivityMapsBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class MapsActivity : AppCompatActivity(), OnMapReadyCallback {
    private lateinit var recyclerView: RecyclerView
    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding
    private lateinit var buttonMap: Button
    private var currentAddress: Address? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        buttonMap = findViewById(R.id.buttonMap)

        buttonMap.setOnClickListener { view: View ->
            if (currentAddress != null) {
                Log.d("MapActivity", "onClick() called")
                val intent: Intent = Intent(this, newActivity::class.java)
                intent.putExtra("address", currentAddress)
                startActivity(intent)
            }
        }
    }



    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        mMap.setOnMapLongClickListener { latLng: LatLng ->
            Log.d("MapsActivity", "Long press at ${latLng.latitude}, ${latLng.longitude}")
            mMap.clear()

            val geocoder = Geocoder(this)
            val results: List<Address> = try {
                geocoder.getFromLocation(
                    latLng.latitude,
                    latLng.longitude,
                    10,
                ) as List<Address>

            } catch (exception: Exception) {
                exception.printStackTrace()
                Log.e("MainActivity", "fail to retrieve results: $exception")
                listOf<Address>()
            }

            if (results.isNotEmpty()) {
                val firstResult = results.first()
                val streetAddress = firstResult.adminArea
                currentAddress = firstResult

                val marker = MarkerOptions().position(latLng).title(streetAddress)
                updateConfirmButtom(firstResult)
                mMap.addMarker(marker)

                recyclerView = findViewById(R.id.news)
                recyclerView.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)

                val newsManager = newsManager()

                //https://stackoverflow.com/questions/65008486/globalscope-vs-coroutinescope-vs-lifecyclescope.
                // GlobalScope.launch(Dispatchers.IO)  this line is reference from above website
                GlobalScope.launch(Dispatchers.IO) {
                    try {
                        val tweets = newsManager.retrieveTweets(
                            streetAddress
                        )
                        val tweet = tweets.toList()
                        runOnUiThread {
                            val adapter = TweetAdapter(tweet)
                            recyclerView.adapter = adapter
                        }
                    } catch (exception: Exception) {
                        runOnUiThread {
                            Toast.makeText(
                                this@MapsActivity,
                                "fail to retrieve tweet",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    }
                }
            }
        }
    }
    private fun updateConfirmButtom(address: Address) {
        buttonMap.text = address.getAddressLine(0)
    }
}