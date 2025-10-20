package com.asmit404.retrofit1

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.eversaleskotlinnewversion.Keys
import dependencies.RetrofitInterface
import okhttp3.ResponseBody
import org.json.JSONArray
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.SocketTimeoutException

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var schoolAdapter: SchoolAdapter
    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        progressBar = findViewById(R.id.progressBar)
        recyclerView = findViewById(R.id.recyclerView)

        schoolAdapter = SchoolAdapter(emptyList())
        recyclerView.adapter = schoolAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        schoolList()
    }

    private fun schoolList() {
        progressBar.visibility = View.VISIBLE
        recyclerView.visibility = View.GONE

        var remoteResponse: String?
        val retrofit = Retrofit.Builder()
            .baseUrl(Keys.testURL)
            .client(
                Retrofit2.getUnsafeOkHttpClient().build()
            )
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val retrofitInterface = retrofit.create(
            RetrofitInterface::class.java
        )
        val call = retrofitInterface.SchoolListWithName()
        call!!.enqueue(object : Callback<ResponseBody?> {

            override fun onResponse(call: Call<ResponseBody?>, response: Response<ResponseBody?>) {
                progressBar.visibility = View.GONE

                if (response.isSuccessful && response.body() != null) {
                    try {
                        remoteResponse = response.body()!!.string()
                        Log.v(Keys.KEY_TAG, "-----response-body----- $remoteResponse")
                        val jsonarray = JSONArray(remoteResponse)
                        val fetchedSchoolItems = mutableListOf<SchoolListItem>()
                        for (i in 0 until jsonarray.length()) {
                            val c: JSONObject = jsonarray.getJSONObject(i)
                            val schoolId = c.optString("Schoolid", "N/A")
                            val name = c.getString("Name")
                            fetchedSchoolItems.add(SchoolListItem(schoolId, name))
                            Log.v(Keys.KEY_TAG, "--Schoolid-----= $schoolId, --Username------$name")
                        }
                        schoolAdapter.updateData(fetchedSchoolItems)
                        recyclerView.visibility = View.VISIBLE
                    } catch (e: Exception) {
                        e.printStackTrace()
                        Toast.makeText(this@MainActivity, "Error parsing data", Toast.LENGTH_SHORT)
                            .show()
                        schoolAdapter.updateData(emptyList())
                        recyclerView.visibility = View.GONE
                    }
                } else {
                    Toast.makeText(
                        this@MainActivity,
                        "Failed to fetch data (Code: ${response.code()})",
                        Toast.LENGTH_SHORT
                    ).show()
                    Log.v(Keys.KEY_TAG, "--errorBody--- " + response.errorBody()?.string())
                    schoolAdapter.updateData(emptyList())
                    recyclerView.visibility = View.GONE
                }
            }

            override fun onFailure(call: Call<ResponseBody?>, t: Throwable) {
                progressBar.visibility = View.GONE
                schoolAdapter.updateData(emptyList())
                recyclerView.visibility = View.GONE

                val toastMessage: String
                val logMessage: String

                if (t is SocketTimeoutException) {
                    toastMessage = "Poor network connection. Please try again."
                    logMessage =
                        "Network Timeout: Poor network connection."
                } else {
                    toastMessage = "Internet Connection Error. Try After Some Time."
                    logMessage =
                        "API Call Failed: ${t.localizedMessage ?: t.message ?: t.toString()}"
                }

                Log.e(
                    Keys.KEY_TAG,
                    logMessage,
                    t
                )
                Toast.makeText(this@MainActivity, toastMessage, Toast.LENGTH_SHORT).show()
            }
        })
    }
}