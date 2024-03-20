package com.example.uasppb.adapter

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.uasppb.R
import com.example.uasppb.databinding.ItemMealBinding
import com.example.uasppb.model.MealsItem
import com.squareup.picasso.Picasso

class MealAdapter : RecyclerView.Adapter<MealAdapter.MealViewHolder>() {
    private val mealList = mutableListOf<MealsItem>()
    private var itemClickListener: ((MealsItem) -> Unit)? = null

    @SuppressLint("NotifyDataSetChanged")
    fun setData(data: List<MealsItem>) {
        mealList.clear()
        mealList.addAll(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_meal, parent, false)
        return MealViewHolder(view)
    }

    override fun onBindViewHolder(holder: MealViewHolder, position: Int) {
        val mealItem = mealList[position]
        holder.bind(mealItem)
    }

    override fun getItemCount(): Int = mealList.size

    inner class MealViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = ItemMealBinding.bind(view)

        fun bind(data: MealsItem) {
            with(binding) {
                tvMealName.text = data.strMeal
                Picasso.get().load(data.strMealThumb).into(ivMeal)
                val bundle = Bundle().apply {
                    putString("mealId", data.idMeal)
                }

                itemView.setOnClickListener {
                    itemClickListener?.invoke(data)
                    val navController = itemView.findNavController()
                    navController.navigate(R.id.action_homeFragment_to_mealDetailFragment, bundle)
                }
            }
        }
    }
}