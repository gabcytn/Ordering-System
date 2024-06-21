package com.example.orderingsystem

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyCheckoutAdapter (private val foodList : ArrayList<FoodItemCheckout>) : RecyclerView.Adapter<MyCheckoutAdapter.MyCheckoutViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyCheckoutViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.food_checkout_item, parent, false)
        return MyCheckoutViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyCheckoutViewHolder, position: Int) {
        val currentItem = foodList[position]
        holder.foodName.text = currentItem.checkoutFoodName
        holder.foodPrice.text = currentItem.checkoutFoodPrice

    }

    override fun getItemCount(): Int {
        return foodList.size
    }

    class MyCheckoutViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val foodName: TextView = itemView.findViewById(R.id.tvCheckoutFoodName)
        val foodPrice: TextView = itemView.findViewById(R.id.tvCheckoutFoodPrice)
    }
}