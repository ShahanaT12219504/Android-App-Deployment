package com.example.deviceconnectivity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.ImageView

class WebAppActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_app)

        val links = mapOf(
            R.id.iccLogo to "https://www.icc-cricket.com/",
            R.id.kickboxLogo to "https://www.wako.sport/",
            R.id.fifaLogo to "https://www.fifa.com/",
            R.id.tableTennisLogo to "https://www.ittf.com/",
            R.id.badmintonLogo to "https://bwfbadminton.com/",
            R.id.swimmingLogo to "https://www.worldaquatics.com/",
            R.id.lawnTennisLogo to "https://www.itftennis.com/",
            R.id.basketballLogo to "https://www.fiba.basketball/",
            R.id.taekwondoLogo to "https://www.worldtaekwondo.org/"
        )

        for ((id, url) in links) {
            findViewById<ImageView>(id).setOnClickListener {
                val intent = Intent(this, WebViewActivity::class.java)
                intent.putExtra("url", url)
                startActivity(intent)
            }
        }
    }
}
