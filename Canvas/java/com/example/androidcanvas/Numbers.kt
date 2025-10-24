package com.example.androidcanvas

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class Numbers : AppCompatActivity() {

    private lateinit var numberButtons: List<Button>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_numbers)

        // Collect all number buttons (0-9)
        numberButtons = listOf(
            findViewById(R.id.btn0),
            findViewById(R.id.btn1),
            findViewById(R.id.btn2),
            findViewById(R.id.btn3),
            findViewById(R.id.btn4),
            findViewById(R.id.btn5),
            findViewById(R.id.btn6),
            findViewById(R.id.btn7),
            findViewById(R.id.btn8),
            findViewById(R.id.btn9)
        )

        // Individual number button click: hide that button
        for (btn in numberButtons) {
            btn.setOnClickListener {
                btn.visibility = View.INVISIBLE
            }
        }

        // All Clear: hide all number buttons at once
        val btnAllClear = findViewById<Button>(R.id.btnAllClear)
        btnAllClear.setOnClickListener {
            for (btn in numberButtons) {
                btn.visibility = View.INVISIBLE
            }
        }

        // All Visible: show all number buttons at once
        val btnAllVisible = findViewById<Button>(R.id.btnAllVisible)
        btnAllVisible.setOnClickListener {
            for (btn in numberButtons) {
                btn.visibility = View.VISIBLE
            }
        }
    }
}
