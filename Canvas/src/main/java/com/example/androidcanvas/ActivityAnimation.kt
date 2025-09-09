package com.example.androidcanvas

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.abs
import kotlin.math.hypot
import kotlin.random.Random

class ActivityAnimation : AppCompatActivity() {

    private lateinit var duck: ImageView
    private lateinit var worm: ImageView
    private lateinit var btnMoveDuck: Button

    private var layoutWidth = 0
    private var layoutHeight = 0
    private var isChasing = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_animation)

        duck = findViewById(R.id.duck)
        worm = findViewById(R.id.worm)
        btnMoveDuck = findViewById(R.id.btnMoveDuck)

        val rootLayout = findViewById<View>(R.id.mainLayout)
        rootLayout.post {
            layoutWidth = rootLayout.width
            layoutHeight = rootLayout.height
        }

        btnMoveDuck.setOnClickListener {
            if (!isChasing) {
                isChasing = true
                chaseLoop()
            }
        }
    }

    private fun chaseLoop() {
        if (!isChasing) return

        val duckX = duck.x
        val duckY = duck.y
        val wormX = worm.x
        val wormY = worm.y

        // Distance between duck and worm
        val dist = hypot((wormX - duckX).toDouble(), (wormY - duckY).toDouble())

        // Move step towards worm
        val step = 30f
        val dx = ((wormX - duckX) / dist * step).toFloat()
        val dy = ((wormY - duckY) / dist * step).toFloat()

        duck.animate().x(duckX + dx).y(duckY + dy).setDuration(100).withEndAction {
            val newDist = abs((duck.x + duck.width / 2) - (worm.x + worm.width / 2)) +
                    abs((duck.y + duck.height / 2) - (worm.y + worm.height / 2))

            // If close enough, worm escapes
            if (newDist < 150) {
                moveWormAway()
            }

            // Continue chase
            chaseLoop()
        }.start()
    }

    private fun moveWormAway() {
        val margin = 100
        val newX = Random.nextInt(margin, layoutWidth - worm.width - margin).toFloat()
        val newY = Random.nextInt(margin, layoutHeight - worm.height - margin).toFloat()

        worm.animate().x(newX).y(newY).setDuration(300).start()
    }
}
