package com.example.foodrecipe.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.foodrecipe.R
import com.example.foodrecipe.entities.CategoryItems
import com.example.foodrecipe.entities.Recipes
import kotlinx.android.synthetic.main.item_rv_sub_category.view.*

class SubCategoryAdapter : RecyclerView.Adapter<SubCategoryAdapter.RecipeViewHolder>() {
    var arrSubCategory = ArrayList<CategoryItems >()
    fun setData(arrData: List<CategoryItems>) {
        arrSubCategory =
            arrData as ArrayList<CategoryItems>
    }

    class RecipeViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        return RecipeViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_rv_sub_category, parent, false))
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        holder.itemView.tv_dish_name.text = arrSubCategory[position].dishName
    }

    override fun getItemCount(): Int {
        return arrSubCategory.size
    }
}