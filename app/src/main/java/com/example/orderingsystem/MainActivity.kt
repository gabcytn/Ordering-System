package com.example.orderingsystem

import android.os.Bundle
import android.util.Log
import android.widget.Adapter
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlin.math.log

class MainActivity : AppCompatActivity() {

    private lateinit var newRecyclerView: RecyclerView
    private lateinit var newArrayList: ArrayList<FoodItem>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        newRecyclerView = findViewById(R.id.rvRecyclerView)
        newRecyclerView.layoutManager = LinearLayoutManager(this)
        newRecyclerView.setHasFixedSize(true)

        newArrayList = arrayListOf()
        getUserData()

        newRecyclerView.adapter = MyAdapter(newArrayList)

        findViewById<Button>(R.id.btnCheckout).setOnClickListener {
            val checkedItems = newArrayList.toMutableList().filter { it.foodIsOrdered }.map { it.foodName }
            var msg = "Your orders are: "
            for(i in checkedItems){
                msg += "$i "
            }


            val finalMessage = if (checkedItems.isEmpty()) "No orders placed" else msg
            Toast.makeText(this, finalMessage, Toast.LENGTH_LONG).show()
        }
    }

    private fun getUserData() {
        val foodNames = listOf("Burger", "Hotdog", "Nuggets", "Noodles", "Fries", "Juice")
        val foodImages = listOf(R.drawable.burger, R.drawable.hotdog, R.drawable.nuggets,
                                R.drawable.noodles, R.drawable.fries, R.drawable.juice)
        val foodPrices = listOf("P199.99", "P29.99", "P79.99", "P39.99", "P99.99", "P29.99")
        for (i in foodNames.indices){
            val foodItem = FoodItem(foodImages[i], foodNames[i], foodPrices[i], false)
            newArrayList.add(foodItem)
        }

    }
}