package com.example.foodrecipe.entities.conventer

import androidx.room.TypeConverter
import com.example.foodrecipe.entities.Category
import com.example.foodrecipe.entities.CategoryItems
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.*

class CategoryListConverter {
    @TypeConverter
    fun fromCategoryList(category: List<CategoryItems>):String?{
        if(category == null){
            return null
        }
        else{
            val gson = Gson()
            val type = object : TypeToken<CategoryItems>(){

            }.type
            return gson.toJson(category,type)
        }
    }

    @TypeConverter
    fun toCategoryList(catgoryString:String):List<CategoryItems>?{
        if(catgoryString==null) return null
        else{
            val gson = Gson()
            val type = object : TypeToken<CategoryItems>(){

            }.type
            return gson.fromJson(catgoryString , type)
        }
    }
}