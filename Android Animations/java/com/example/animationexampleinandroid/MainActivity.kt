package com.example.animationexampleinandroid

import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val imageView = findViewById<ImageView>(R.id.imageView)
        val btnFade = findViewById<Button>(R.id.btnFade)
        val btnMove = findViewById<Button>(R.id.btnMove)

        btnFade.setOnClickListener {
            // Toggle fade in and fade out
            val anim = AnimationUtils.loadAnimation(this, R.anim.fade_in)
            imageView.startAnimation(anim)
        }

        btnMove.setOnClickListener {
            val anim = AnimationUtils.loadAnimation(this, R.anim.move)
            imageView.startAnimation(anim)
        }
    }
}