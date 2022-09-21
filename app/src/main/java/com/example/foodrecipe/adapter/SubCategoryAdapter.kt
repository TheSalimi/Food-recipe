package com.example.foodrecipe.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.foodrecipe.R
import com.example.foodrecipe.entities.CategoryItems
import com.example.foodrecipe.entities.MealsItems
import com.example.foodrecipe.entities.Recipes
import kotlinx.android.synthetic.main.item_rv_main_category.view.*
import kotlinx.android.synthetic.main.item_rv_sub_category.view.*
import kotlinx.android.synthetic.main.item_rv_sub_category.view.img_dish

class SubCategoryAdapter : RecyclerView.Adapter<SubCategoryAdapter.RecipeViewHolder>() {
    var listener : SubCategoryAdapter.onItemClickListener?=null
    var arrSubCategory = ArrayList<MealsItems>()
    var ctx : Context?=null
    fun setData(arrData: List<MealsItems>) {
        arrSubCategory =
            arrData as ArrayList<MealsItems>
    }

    fun setClicklistener(listener1 : SubCategoryAdapter.onItemClickListener){
        listener = listener1
    }
    class RecipeViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        ctx = parent.context
        return RecipeViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_rv_sub_category, parent, false))
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        holder.itemView.tv_dish_name.text = arrSubCategory[position].strMeal
        Glide.with(ctx!!).load(arrSubCategory[position].strMealThumb).into(holder.itemView.img_dish)
        holder.itemView.rootView.setOnClickListener{
            listener!!.onClicked(arrSubCategory[position].id)
        }
    }

    override fun getItemCount(): Int {
        return arrSubCategory.size
    }

    interface onItemClickListener{
        fun onClicked(id : Int)
    }
}