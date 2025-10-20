package com.example.unit3

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.RelativeLayout
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val overlay1 = findViewById<RelativeLayout>(R.id.overlay1)
        val overlay2 = findViewById<RelativeLayout>(R.id.overlay2)
        val overlay3 = findViewById<RelativeLayout>(R.id.overlay3)

        val button1 = findViewById<Button>(R.id.button1)
        val button2 = findViewById<Button>(R.id.button2)
        val button3 = findViewById<Button>(R.id.button3)

        button1.setOnClickListener {
            overlay1.visibility = View.GONE
        }

        button2.setOnClickListener {
            overlay2.visibility = View.GONE
        }

        button3.setOnClickListener {
            overlay3.visibility = View.GONE
        }
    }
}
