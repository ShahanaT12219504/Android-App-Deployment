package com.example.androidcanvas

import android.animation.ObjectAnimator
import android.os.Bundle
import android.widget.Button
import android.widget.ViewAnimator
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class ActivityBall : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ball)

        val ballView = findViewById<View>(R.id.ballView)
        val dropButton = findViewById<Button>(R.id.dropButton)

        dropButton.setOnClickListener {
            // Distance from ball top to just above the button
            val buttonY = dropButton.y       // top of button
            val ballHeight = ballView.height
            val targetY = buttonY - ballHeight - 16f // 16 for margin above button

            // Animate the ball's translationY
            ObjectAnimator.ofFloat(ballView, "translationY", targetY).apply {
                duration = 1000 // 1 second
                start()
            }
        }
    }
}
