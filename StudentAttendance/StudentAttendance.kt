package com.example.unit3

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.GridLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView

class StudentAttendance : AppCompatActivity() {

    data class Student(
        val name: String,
        val uid: String,
        val imageRes: Int
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_attendance)

        // toolbar title already set in XML

        val gridLayout = findViewById<GridLayout>(R.id.gridLayout)

        val students = listOf(
            Student("Alice", "UID001", R.drawable.ic_launcher_foreground),
            Student("Bob", "UID002", R.drawable.ic_launcher_foreground),
            Student("Charlie", "UID003", R.drawable.ic_launcher_foreground),
            Student("David", "UID004", R.drawable.ic_launcher_foreground),
            Student("Eve", "UID005", R.drawable.ic_launcher_foreground)
        )

        val inflater = LayoutInflater.from(this)

        for (student in students) {
            val card = inflater.inflate(R.layout.student_card, gridLayout, false) as CardView
            val img = card.findViewById<ImageView>(R.id.studentImage)
            val nameTv = card.findViewById<TextView>(R.id.studentName)
            val uidTv = card.findViewById<TextView>(R.id.studentUid)
            val button = card.findViewById<Button>(R.id.attendanceButton)

            img.setImageResource(student.imageRes)
            nameTv.text = student.name
            uidTv.text = student.uid

            // Default absent state
            nameTv.setTextColor(Color.RED)
            button.setBackgroundColor(Color.RED)
            button.text = "Absent"

            button.setOnClickListener {
                if (button.text == "Absent") {
                    button.text = "Present"
                    button.setBackgroundColor(Color.GREEN)
                    nameTv.setTextColor(Color.GREEN)
                } else {
                    button.text = "Absent"
                    button.setBackgroundColor(Color.RED)
                    nameTv.setTextColor(Color.RED)
                }
            }

            gridLayout.addView(card)
        }
    }
}
