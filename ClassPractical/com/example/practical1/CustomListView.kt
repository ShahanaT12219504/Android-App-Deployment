package com.example.practical1

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ListView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class CustomListView : AppCompatActivity() {
    private lateinit var listView: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_custom_list_view)
        listView = findViewById(R.id.listView)

        val myItems = listOf(
            MyItem(
                "Mobile App Development",
                "This is an Android App",
                R.drawable.ic_launcher_background
            ),
            MyItem(
                "ios App Development",
                "This is an iphone App",
                R.drawable.ic_launcher_background
            ),
            MyItem("Web App Development", "This is a Web App", R.drawable.ic_launcher_background)
        )

        val adapter = MyAdapter(this, myItems)
        listView.adapter = adapter

        listView.setOnItemClickListener(object : AdapterView.OnItemClickListener {
            override fun onItemClick(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                val item = myItems[position]
                Toast.makeText(this@CustomListView, "Clicked: ${item.title}", Toast.LENGTH_SHORT)
            }
        })


    }
}