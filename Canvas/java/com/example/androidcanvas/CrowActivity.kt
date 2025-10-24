package com.example.androidcanvas

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.graphics.Path
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.RelativeLayout
import androidx.appcompat.app.AppCompatActivity

class CrowActivity : AppCompatActivity() {

    private lateinit var crow: ImageView
    private lateinit var glass: ImageView
    private lateinit var btnStart: Button
    private lateinit var rootLayout: RelativeLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crow)

        rootLayout = findViewById(R.id.rootLayout)
        crow = findViewById(R.id.crow)
        glass = findViewById(R.id.glass)
        btnStart = findViewById(R.id.btnStart)

        btnStart.setOnClickListener {
            startCrowAnimation()
        }
    }

    private fun startCrowAnimation() {
        // Step 1: Crow flies from left to birdbath in a curved path
        val startX = crow.x
        val startY = crow.y
        val endX = glass.x + glass.width / 2 - crow.width / 2
        val endY = glass.y - crow.height / 2

        val path = Path().apply {
            moveTo(startX, startY)
            // Curve: first up, then down to glass
            cubicTo(
                startX + 200, startY - 300,  // control point 1
                endX - 200, endY - 100,      // control point 2
                endX, endY                   // end point
            )
        }

        val flyToGlass = ObjectAnimator.ofFloat(crow, "x", "y", path)
        flyToGlass.duration = 2500
        flyToGlass.start()

        flyToGlass.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                super.onAnimationEnd(animation)
                // Step 2: Crow "drinks" for 1 second
                crow.postDelayed({
                    // Step 3: Fly away to right off-screen in a curve
                    val flyAwayPath = Path().apply {
                        moveTo(crow.x, crow.y)
                        cubicTo(
                            crow.x + 100, crow.y - 200,
                            rootLayout.width.toFloat(), crow.y - 100,
                            rootLayout.width.toFloat(), 50f
                        )
                    }
                    val flyAway = ObjectAnimator.ofFloat(crow, "x", "y", flyAwayPath)
                    flyAway.duration = 2500
                    flyAway.start()
                }, 1000)
            }
        })
    }
}