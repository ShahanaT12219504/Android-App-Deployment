package com.example.recyclerviewinandroidproject

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)

        val items = listOf(
            MyItem("Apple", R.drawable.ic_launcher_foreground),
            MyItem("Banana", R.drawable.ic_launcher_foreground),
            MyItem("Orange", R.drawable.ic_launcher_foreground),
            MyItem("Mango", R.drawable.ic_launcher_foreground),
            MyItem("Grapes", R.drawable.ic_launcher_foreground)
        )

        recyclerView.layoutManager = LinearLayoutManager(this)

        val adapter = MyAdapter(items) { item ->
            Toast.makeText(this, "Clicked: ${item.name}", Toast.LENGTH_SHORT).show()
        }
        recyclerView.adapter = adapter
    }
}