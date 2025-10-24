package com.example.androidcanvas

import android.os.Bundle
import android.transition.Slide
import android.transition.TransitionManager
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Transition : AppCompatActivity() {
    private var isVisible = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_transition)

        val rootLayout = findViewById<LinearLayout>(R.id.main)
        val textView = findViewById<TextView>(R.id.transitionTV)
        val button = findViewById<Button>(R.id.transitionBtn)

        button.setOnClickListener {
            val transition = Slide(Gravity.END)
            transition.duration = 600

            TransitionManager.beginDelayedTransition(rootLayout, transition)
            textView.visibility = if (isVisible) View.GONE else View.VISIBLE
            isVisible = !isVisible
        }
    }
}