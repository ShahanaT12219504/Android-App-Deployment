package com.example.practical1

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class Intent_Toast : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intent_toast)

        val intentBtn = findViewById<Button>(R.id.intentBtn)
        val toastBtn = findViewById<Button>(R.id.toastBtn)

        intentBtn.setOnClickListener {
            val i = Intent(this, MainActivity::class.java)
            startActivity(i)
        }

        toastBtn.setOnClickListener {
            Toast.makeText(this, "Hello All", Toast.LENGTH_SHORT).show()
        }
    }
}
