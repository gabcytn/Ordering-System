package com.example.orderingsystem

import android.content.Intent
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

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

        findViewById<Button>(R.id.btnAddtoCart).setOnClickListener {
            val checkedItemNames = newArrayList.toMutableList().filter { it.foodIsOrdered }.map { it.foodName }
            val checkedItemPrices = newArrayList.toMutableList().filter { it.foodIsOrdered }.map { it.foodPrice }


            val intent = Intent(this, CheckoutActivity::class.java)
            if (checkedItemNames.isNotEmpty()) {
                intent.putExtra("EXTRA_FOOD", ArrayList(checkedItemNames))
                intent.putExtra("EXTRA_PRICE", ArrayList(checkedItemPrices))
                startActivity(intent)
            }

            Toast(this).apply {
                duration = Toast.LENGTH_SHORT
                view = layoutInflater.inflate(R.layout.toast_custom, findViewById(R.id.cvToast))
                show()
            }
//            return@setOnClickListener
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