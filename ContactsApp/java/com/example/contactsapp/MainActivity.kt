package com.example.contactsapp

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private val contacts = listOf(
        Contact("Shah", "+91 98765 00001"),
        Contact("Chelsa", "+91 98765 00002"),
        Contact("Sudhi", "+91 98765 00003"),
        Contact("Asmin", "+91 98765 00004"),
        Contact("KaiKai", "+91 98765 00005")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerContacts)
        val adapter = ContactAdapter(contacts) { contact ->
            Toast.makeText(this, "Calling ${contact.name}", Toast.LENGTH_SHORT).show()
        }

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }
}
