package com.example.customgridview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView

class GridAdapter(private val context: Context, private val items: List<GridItem>) : BaseAdapter() {
    private val inflater = LayoutInflater.from(context)

    override fun getCount(): Int = items.size

    override fun getItem(position: Int): Any = items[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val rowView = convertView ?: inflater.inflate(R.layout.grid_item, parent, false)

        val imageView = rowView.findViewById<ImageView>(R.id.imgV)
        val nameText = rowView.findViewById<TextView>(R.id.name)
        val descText = rowView.findViewById<TextView>(R.id.desc)
        val priceText = rowView.findViewById<TextView>(R.id.price)

        val item = items[position]
        imageView.setImageResource(item.imageResId)
        nameText.text = item.name
        descText.text = item.description
        priceText.text = item.price

        return rowView
    }
}
