package com.example.customgridview

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class MainActivity : AppCompatActivity() {

    private lateinit var teamRecyclerView: RecyclerView
    private lateinit var teamAdapter: TeamAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        teamRecyclerView = findViewById(R.id.teamRecyclerView)
        teamRecyclerView.layoutManager = LinearLayoutManager(this)

        val teams = listOf(
            Team(1, "Australia", R.drawable.flag_australia, "Aggressive team", "Great bowling attack"),
            Team(2, "England", R.drawable.flag_england, "Home advantage", "Balanced squad"),
            Team(3, "Pakistan", R.drawable.flag_pakistan, "Dynamic players", "Strong bowling unit"),
            Team(4, "South Africa", R.drawable.flag_southafrica, "Power hitters", "Fast bowling strength"),
            Team(5, "Sri Lanka", R.drawable.flag_srilanka, "Experienced team", "Skilled spinners"),
            Team(6, "Bangladesh", R.drawable.flag_bangladesh, "Rising team", "Improving batting"),
            Team(7, "West Indies", R.drawable.flag_westindies, "Explosive players", "Good fielding"),
            Team(8, "Afghanistan", R.drawable.flag_afghanistan, "Young talent", "Spin bowling focus")
        )

        teamAdapter = TeamAdapter(teams)
        teamRecyclerView.adapter = teamAdapter
    }
}
