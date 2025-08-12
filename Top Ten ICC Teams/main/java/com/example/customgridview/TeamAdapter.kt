package com.example.customgridview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TeamAdapter(private val teams: List<Team>) : RecyclerView.Adapter<TeamAdapter.TeamViewHolder>() {

    class TeamViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val flagImageView: ImageView = itemView.findViewById(R.id.flagImageView)
        val rankTextView: TextView = itemView.findViewById(R.id.rankTextView)
        val teamNameTextView: TextView = itemView.findViewById(R.id.teamNameTextView)
        val teamDescTextView: TextView = itemView.findViewById(R.id.teamDescTextView)
        val teamInfoTextView: TextView = itemView.findViewById(R.id.teamInfoTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_team, parent, false)
        return TeamViewHolder(view)
    }

    override fun onBindViewHolder(holder: TeamViewHolder, position: Int) {
        val team = teams[position]
        holder.flagImageView.setImageResource(team.flagResId)
        holder.rankTextView.text = team.rank.toString()
        holder.teamNameTextView.text = team.name
        holder.teamDescTextView.text = team.description
        holder.teamInfoTextView.text = team.info
    }

    override fun getItemCount() = teams.size
}
