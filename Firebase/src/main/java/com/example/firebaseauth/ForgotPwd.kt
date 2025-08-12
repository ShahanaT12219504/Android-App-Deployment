package com.example.firebaseauth

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class ForgotPwd : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var emailEditText:  EditText
    private lateinit var resetBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_pwd)

        auth = FirebaseAuth.getInstance()
        emailEditText = findViewById(R.id.emailEditText)
        resetBtn = findViewById(R.id.resetBtn)

        resetBtn.setOnClickListener {
            val email = emailEditText.text.toString().trim()
            if (email.isEmpty()) {
                emailEditText.error = "Please enter ur email"
                return@setOnClickListener
            }
            auth.sendPasswordResetEmail(email)
                .addOnCompleteListener { task ->
                    if(task.isSuccessful){
                        Toast.makeText(this,"Password reset email sent",
                            Toast.LENGTH_SHORT)
                            .show()
                } else{
                        Toast.makeText(this,"Error: ${task.exception?.message}",
                        Toast.LENGTH_SHORT).show()
                    }

            }
        }
    }
}
