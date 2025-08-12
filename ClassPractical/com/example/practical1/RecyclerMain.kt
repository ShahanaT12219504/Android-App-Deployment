package com.example.practical1

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.unit1.RecyclerAdapter
import com.example.practical1.RecyclerItem

class RecyclerMain : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_recycler_main)

        recyclerView = findViewById(R.id.recyclerView)

        val items = listOf(
            RecyclerItem("1", R.drawable.ic_launcher_background),
            RecyclerItem("2", R.drawable.ic_launcher_background),
            RecyclerItem("3", R.drawable.ic_launcher_background),
            RecyclerItem("4", R.drawable.ic_launcher_background),
            RecyclerItem("5", R.drawable.ic_launcher_background),
            RecyclerItem("6", R.drawable.ic_launcher_background)
        )

        recyclerView.layoutManager = LinearLayoutManager(this)

        val adapter = RecyclerAdapter(items) { item ->
            Toast.makeText(this, "clicked: ${item.name}", Toast.LENGTH_SHORT).show()
        }

        recyclerView.adapter = adapter
    }
}
