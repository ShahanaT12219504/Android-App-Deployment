package com.asmit404.retrofit1

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

data class SchoolListItem(val id: String, val name: String)

class SchoolAdapter(private var schools: List<SchoolListItem>) :
    RecyclerView.Adapter<SchoolAdapter.SchoolViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SchoolViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_school, parent, false) // Assumes item_school.xml
        return SchoolViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: SchoolViewHolder, position: Int) {
        val currentItem = schools[position]
        holder.schoolIdTextView.text = "ID: ${currentItem.id}"
        holder.schoolNameTextView.text = "Name: ${currentItem.name}"
    }

    override fun getItemCount() = schools.size

    class SchoolViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val schoolIdTextView: TextView = itemView.findViewById(R.id.textViewSchoolId) // Assumes id in item_school.xml
        val schoolNameTextView: TextView = itemView.findViewById(R.id.textViewSchoolName) // Assumes id in item_school.xml
    }

    fun updateData(newSchools: List<SchoolListItem>) {
        schools = newSchools
        notifyDataSetChanged() // Consider using DiffUtil for better performance
    }
}