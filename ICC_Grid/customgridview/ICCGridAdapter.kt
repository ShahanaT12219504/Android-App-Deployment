package com.example.customgridview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView

class ICCGridAdapter(
    private val context: Context,
    private val teams: List<ICCTeams>
) : ArrayAdapter<ICCTeams>(context, 0, teams) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(context)
            .inflate(R.layout.item_icc_grid, parent, false)

        val team = getItem(position)

        val flagImage = view.findViewById<ImageView>(R.id.flagImage)
        val teamName = view.findViewById<TextView>(R.id.teamName)
        val teamInfo = view.findViewById<TextView>(R.id.teamInfo)
        val ratingBar = view.findViewById<RatingBar>(R.id.ratingBar)

        team?.let {
            flagImage.setImageResource(it.flagResId)
            teamName.text = "${it.rank}. ${it.name}"
            teamInfo.text = it.info

            val maxRating = 125f
            val stars = (it.rating / maxRating) * 5f
            ratingBar.rating = stars.coerceAtMost(5f)
        }

        return view
    }
}
