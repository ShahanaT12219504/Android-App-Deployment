package com.example.customgridview

import android.os.Bundle
import android.widget.GridView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class GridViewActivity : AppCompatActivity() {

    private lateinit var gridView: GridView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_grid_view)

        gridView = findViewById(R.id.gridView)

        val items = listOf(
            GridItem("Christian Louboutin", "Fresh red apples", "$1.99", R.drawable.img),
            GridItem("Loafers", "Organic bananas", "$0.99", R.drawable.img_1),
            GridItem("Trekking Boots", "Sweet oranges", "$2.49", R.drawable.img_2),
            GridItem("Boots", "Seedless grapes", "$3.29", R.drawable.img_3),
            GridItem("Birkenstock", "Ripe mangoes", "$2.99", R.drawable.img_4),
            GridItem("Sambas", "Juicy pineapples", "$4.00", R.drawable.img_5)
        )

        val adapter = GridAdapter(this, items)
        gridView.adapter = adapter

        gridView.setOnItemClickListener { _, _, position, _ ->
            val item = items[position]
            Toast.makeText(this, "Clicked: ${item.name}", Toast.LENGTH_SHORT).show()
        }
    }
}
