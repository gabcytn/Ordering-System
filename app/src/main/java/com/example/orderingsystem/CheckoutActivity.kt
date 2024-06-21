package com.example.orderingsystem

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.math.BigDecimal

class CheckoutActivity : AppCompatActivity() {

    private lateinit var newRecyclerView: RecyclerView
    private lateinit var newArrayList: ArrayList<FoodItemCheckout>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checkout)

        newRecyclerView = findViewById(R.id.rvCheckoutRecycler)
        newRecyclerView.layoutManager = LinearLayoutManager(this)
        newRecyclerView.setHasFixedSize(true)

        newArrayList = arrayListOf()
        getUserData()

        newRecyclerView.adapter = MyCheckoutAdapter(newArrayList)



        findViewById<Button>(R.id.btnCheckout).setOnClickListener {
            finish()
        }
    }

    private fun getUserData() {

        val foods = intent.getStringArrayListExtra("EXTRA_FOOD")?.toList()
        val prices = intent.getStringArrayListExtra("EXTRA_PRICE")?.toList()
        val pricesDouble = mutableListOf<BigDecimal>()

        val foodsList = mutableListOf<String>()
        if (foods != null) {
            for (i in foods){
                foodsList.add(i)
            }
        }
        if (prices != null) {
            for (i in prices.indices){
                val i = prices[i].replace("P", "")
                pricesDouble.add(i.toBigDecimal())
            }
        }

        var sum = BigDecimal(0)
        for (i in pricesDouble){
            sum += i
        }


        findViewById<TextView>(R.id.tvTotalAmount).text = "P${sum}"

        if (foods != null && prices != null) {
            for (i in foods.indices){
                val foodItem = FoodItemCheckout(foods[i], prices[i])
                newArrayList.add(foodItem)
            }
        }

    }
}