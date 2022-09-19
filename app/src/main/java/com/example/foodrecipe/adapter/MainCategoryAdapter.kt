package com.example.foodrecipe.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.foodrecipe.R
import com.example.foodrecipe.entities.CategoryItems
import com.example.foodrecipe.entities.Recipes
import kotlinx.android.synthetic.main.item_rv_main_category.view.*

class MainCategoryAdapter : RecyclerView.Adapter<MainCategoryAdapter.RecipeViewHolder>() {
    var ctx:Context?=null
    var arrMainCategory = ArrayList<CategoryItems>()
    fun setData(arrData: List<CategoryItems>) {
        arrMainCategory =
            arrData as ArrayList<CategoryItems>
    }

    class RecipeViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        ctx = parent.context
        return RecipeViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_rv_main_category, parent, false))
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        holder.itemView.tv_main_name.text = arrMainCategory[position].strCategory
        Glide.with(ctx!!).load(arrMainCategory[position].strCategoryThumb).into(holder.itemView.img_dish)
    }

    override fun getItemCount(): Int {
        return arrMainCategory.size
    }
}