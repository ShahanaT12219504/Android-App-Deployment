package com.example.unit3

import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class Timer : AppCompatActivity() {

    private lateinit var timers: List<TextView>
    private lateinit var btnStart: Button
    private lateinit var btnStop: Button
    private lateinit var btnRestart: Button
    private lateinit var btnFinish: Button

    private var countdownTimers = mutableListOf<CountDownTimer?>()
    private val startTimes = listOf(5, 4, 3, 2, 1) // minutes

    private var isRunning = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_timer)

        timers = listOf(
            findViewById(R.id.timer1),
            findViewById(R.id.timer2),
            findViewById(R.id.timer3),
            findViewById(R.id.timer4),
            findViewById(R.id.timer5)
        )

        btnStart = findViewById(R.id.btnStart)
        btnStop = findViewById(R.id.btnStop)
        btnRestart = findViewById(R.id.btnRestart)
        btnFinish = findViewById(R.id.btnFinish)

        countdownTimers.addAll(List(startTimes.size) { null })

        // set initial timer texts
        for (i in startTimes.indices) {
            timers[i].text = String.format("%d:00", startTimes[i])
        }

        btnStart.setOnClickListener {
            if (!isRunning) {
                isRunning = true
                startTimer(0)
            }
        }

        btnStop.setOnClickListener {
            stopAllTimers()
        }

        btnRestart.setOnClickListener {
            stopAllTimers()
            resetTimers()
        }

        btnFinish.setOnClickListener {
            stopAllTimers()
            finishTimers()
        }
    }

    private fun startTimer(index: Int) {
        if (index >= startTimes.size || countdownTimers[index] != null) return

        val totalMillis = startTimes[index] * 60 * 1000L
        countdownTimers[index] = object : CountDownTimer(totalMillis, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                val minutes = (millisUntilFinished / 1000) / 60
                val seconds = (millisUntilFinished / 1000) % 60
                timers[index].text = String.format("%d:%02d", minutes, seconds)

                // trigger next timer
                if (index + 1 < startTimes.size &&
                    millisUntilFinished <= startTimes[index + 1] * 60 * 1000L &&
                    countdownTimers[index + 1] == null
                ) {
                    startTimer(index + 1)
                }
            }

            override fun onFinish() {
                timers[index].text = "0:00"
            }
        }.start()
    }

    private fun stopAllTimers() {
        for (i in countdownTimers.indices) {
            countdownTimers[i]?.cancel()
            countdownTimers[i] = null
        }
        isRunning = false
    }

    private fun resetTimers() {
        countdownTimers.clear()
        countdownTimers.addAll(List(startTimes.size) { null })
        for (i in startTimes.indices) {
            timers[i].text = String.format("%d:00", startTimes[i])
        }
    }

    private fun finishTimers() {
        for (i in startTimes.indices) {
            timers[i].text = "0:00"
        }
        countdownTimers.clear()
        countdownTimers.addAll(List(startTimes.size) { null })
        isRunning = false
    }
}
