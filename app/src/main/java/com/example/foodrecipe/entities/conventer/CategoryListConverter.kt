package com.example.foodrecipe.entities.conventer

import androidx.room.TypeConverter
import com.example.foodrecipe.entities.Category
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.*

class CategoryListConverter {
    @TypeConverter
    fun fromCategoryList(category: List<Category>):String?{
        if(category == null){
            return null
        }
        else{
            val gson = Gson()
            val type = object : TypeToken<Category>(){

            }.type
            return gson.toJson(category,type)
        }
    }

    @TypeConverter
    fun toCategoryList(catgoryString:String):List<Category>?{
        if(catgoryString==null) return null
        else{
            val gson = Gson()
            val type = object : TypeToken<Category>(){

            }.type
            return gson.fromJson(catgoryString , type)
        }
    }
}