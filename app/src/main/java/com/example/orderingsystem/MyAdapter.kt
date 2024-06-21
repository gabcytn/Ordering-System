package com.example.orderingsystem

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class MyAdapter(private val foodList: ArrayList<FoodItem>) : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.food_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return foodList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = foodList[position]
        holder.foodImage.setImageResource(currentItem.foodImage)
        holder.foodName.text = currentItem.foodName
        holder.foodPrice.text = currentItem.foodPrice
        holder.foodCheck.isChecked = currentItem.foodIsOrdered
        holder.foodCheck.text = currentItem.foodName

        holder.foodCheck.setOnCheckedChangeListener{ _, isChecked ->
            currentItem.foodIsOrdered = isChecked
        }
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val foodImage: ImageView = itemView.findViewById(R.id.ivFoodImage)
        val foodName: TextView = itemView.findViewById(R.id.tvFoodName)
        val foodPrice: TextView = itemView.findViewById(R.id.tvFoodPrice)
        val foodCheck: CheckBox = itemView.findViewById(R.id.cbFoodCheck)

        init {
            itemView.setOnClickListener{
                if (this.foodCheck.isChecked){
                    this.foodCheck.isChecked = false
                } else {
                    this.foodCheck.isChecked = true
                }
            }
        }
    }
}