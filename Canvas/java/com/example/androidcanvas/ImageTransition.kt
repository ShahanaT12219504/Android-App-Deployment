package com.example.androidcanvas

import android.os.Bundle
import android.transition.Slide
import android.transition.TransitionManager
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat.enableEdgeToEdge
import androidx.core.view.WindowInsetsCompat

class ImageTransition : AppCompatActivity() {
    private var isVisible = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_image_transition)

        val rootLayout = findViewById<LinearLayout>(R.id.imagemain)
        val image1 = findViewById<ImageView>(R.id.imgView1)
        val image2 = findViewById<ImageView>(R.id.imgView2)
        val button = findViewById<Button>(R.id.btnTransiton)

        button.setOnClickListener {
            val transition = Slide(Gravity.END)
            transition.duration = 600

            TransitionManager.beginDelayedTransition(rootLayout, transition)
            image1.visibility = if (isVisible) View.GONE else View.VISIBLE
            image2.visibility = if (isVisible) View.GONE else View.VISIBLE
            isVisible = !isVisible
        }
    }

}