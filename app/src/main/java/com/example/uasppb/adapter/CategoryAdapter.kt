package com.example.uasppb.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.uasppb.R
import com.example.uasppb.databinding.ItemCategoryBinding
import com.example.uasppb.model.CategoryItem

class CategoryAdapter : RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    private val categoryList = mutableListOf<CategoryItem>()
    private val _itemClickListener = MutableLiveData<CategoryItem>()
    val itemClickListener: LiveData<CategoryItem> = _itemClickListener

    @SuppressLint("NotifyDataSetChanged")
    fun setData(newListData: List<CategoryItem>) {
        categoryList.clear()
        categoryList.addAll(newListData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.item_category, parent, false
        )
        return CategoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val categoryItem = categoryList[position]
        holder.bind(categoryItem)
    }

    override fun getItemCount(): Int = categoryList.size

    inner class CategoryViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = ItemCategoryBinding.bind(view)
        private var isClickable = true

        fun bind(data: CategoryItem) {
            with(binding) {
                tvCategoryName.text = data.strCategory
                itemView.setOnClickListener {
                    _itemClickListener.value = data
                }
            }
        }
    }
}