package com.example.firebaseauth

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class HomePage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_page)

        findViewById<Button>(R.id.exitBtn).setOnClickListener {
            confirmExit()
        }
    }

    override fun onBackPressed() {
        confirmExit()
    }

    private fun confirmExit() {
        AlertDialog.Builder(this)
            .setTitle("Exit App")
            .setMessage("Do you want to exit?")
            .setPositiveButton("Yes") { _, _ -> finishAffinity() }
            .setNegativeButton("No", null)
            .show()
    }
}
