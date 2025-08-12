package com.example.firebaseauth

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var email: EditText
    private lateinit var password: EditText
    private lateinit var regBtn: Button
    private lateinit var backToLogin: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        auth = FirebaseAuth.getInstance()

        email = findViewById(R.id.emailR)
        password = findViewById(R.id.pwdR)
        regBtn = findViewById(R.id.regBtn)
        backToLogin = findViewById(R.id.backToLogin)

        regBtn.setOnClickListener {
            val e = email.text.toString()
            val p = password.text.toString()
            if (e.isNotEmpty() && p.isNotEmpty()) {
                auth.createUserWithEmailAndPassword(e, p)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            Toast.makeText(this, "Successfully Registered", Toast.LENGTH_LONG).show()
                            startActivity(Intent(this, ActivityLogin::class.java))
                            finish()
                        } else {
                            Toast.makeText(this, "Registration failed: ${task.exception?.message}", Toast.LENGTH_LONG).show()
                        }
                    }
            } else {
                Toast.makeText(this, "Empty Fields Are not Allowed!!", Toast.LENGTH_SHORT).show()
            }
        }

        backToLogin.setOnClickListener {
            startActivity(Intent(this, ActivityLogin::class.java))
            finish()
        }
    }
}
