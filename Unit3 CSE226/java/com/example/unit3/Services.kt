package com.example.unit3

import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.os.PersistableBundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class Services : AppCompatActivity() {
    private var boundService: MyBoundService? = null
    private var isBound = false

    private val connection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            isBound = true
            Toast.makeText(
                this@Services, boundService?.getWelcomeMessage(),
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        val startStartedService=findViewById<Button>(R.id.btnStartStartedService)
        val stopStartedService=findViewById<Button>(R.id.btnStopStartedService)
        val bindBoundService=findViewById<Button>(R.id.btnBindBoundService)
        val unbindBoundService=findViewById<Button>(R.id.btnUnbindBoundService)
        val startForegroundService=findViewById<Button>(R.id.btnStartForegroundService)
        val stopForegroundService=findViewById<Button>(R.id.btnStopForegroundService)

        startStartedService.setOnClickListener {
            startService(Intent(this, MyStartedService::class.java))
        }

        stopStartedService.setOnClickListener {
            stopService(Intent(this, MyStartedService::class.java))
        }

        bindBoundService.setOnClickListener {
            val intent = Intent(this, MyBoundService::class.java)
            bindService(intent, connection, BIND_AUTO_CREATE)
        }

        unbindBoundService.setOnClickListener {
            if (isBound) {
                unbindService(connection)
                isBound = false
            }
        }

        startForegroundService.setOnClickListener {
            startService(Intent(this, MyForegroundService::class.java))
        }

        stopForegroundService.setOnClickListener {
            stopService(Intent(this, MyForegroundService::class.java))
        }

    }
}