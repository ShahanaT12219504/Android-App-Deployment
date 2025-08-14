package com.example.customgridview

import android.os.Bundle
import android.widget.GridView
import androidx.appcompat.app.AppCompatActivity

class ICCCustomGrid : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_icccustom_grid)

        val gridView: GridView = findViewById(R.id.teamGridView)

        val teams = listOf(
            ICCTeams(1, "India", 122, "Current ODI Champions", R.drawable.flag_india),
            ICCTeams(2, "Australia", 118, "5-time World Cup winners", R.drawable.flag_australia),
            ICCTeams(3, "South Africa", 114, "Strong bowling attack", R.drawable.flag_southafrica),
            ICCTeams(4, "Pakistan", 110, "Skilled in limited overs", R.drawable.flag_pakistan),
            ICCTeams(5, "New Zealand", 108, "Consistent performers", R.drawable.flag_new_zealand),
            ICCTeams(6, "England", 106, "2019 World Cup winners", R.drawable.flag_england),
            ICCTeams(7, "Sri Lanka", 101, "1996 World Cup winners", R.drawable.flag_srilanka),
            ICCTeams(8, "Afghanistan", 95, "Emerging cricket power", R.drawable.flag_afghanistan),
            ICCTeams(9, "Bangladesh", 90, "Rising Asian team", R.drawable.flag_bangladesh),
            ICCTeams(10, "West Indies", 85, "2-time World Cup winners", R.drawable.flag_westindies)
        )

        val adapter = ICCGridAdapter(this, teams)
        gridView.adapter = adapter
    }
}
