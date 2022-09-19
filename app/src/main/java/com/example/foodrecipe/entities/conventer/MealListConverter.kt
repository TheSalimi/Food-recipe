package com.example.foodrecipe.entities.conventer

import androidx.room.TypeConverter
import com.example.foodrecipe.entities.Category
import com.example.foodrecipe.entities.Meal
import com.example.foodrecipe.entities.MealsItems
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.*

class MealListConverter {
    @TypeConverter
    fun fromCategoryList(meals: List<MealsItems>):String?{
        if(meals == null){
            return null
        }
        else{
            val gson = Gson()
            val type = object : TypeToken<MealsItems>(){

            }.type
            return gson.toJson(meals,type)
        }
    }

    @TypeConverter
    fun toCategoryList(mealsString:String):List<MealsItems>?{
        if(mealsString==null) return null
        else{
            val gson = Gson()
            val type = object : TypeToken<MealsItems>(){

            }.type
            return gson.fromJson(mealsString , type)
        }
    }
}