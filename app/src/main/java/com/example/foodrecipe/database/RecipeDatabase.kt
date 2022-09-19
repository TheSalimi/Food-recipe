package com.example.foodrecipe.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.foodrecipe.dao.RecipeDao
import com.example.foodrecipe.entities.*
import com.example.foodrecipe.entities.conventer.CategoryListConverter
import com.example.foodrecipe.entities.conventer.MealListConverter
import java.security.AccessControlContext

@Database(entities = [Recipes::class , CategoryItems::class , Category::class,Meal::class,MealsItems::class] , version = 1 , exportSchema = false)
@TypeConverters(MealListConverter::class, CategoryListConverter::class)
abstract class RecipeDatabase : RoomDatabase() {
    companion object{
        var recipeDatabase:RecipeDatabase?=null
        @Synchronized
        fun getDatabase(context: Context): RecipeDatabase{
            if(recipeDatabase==null){
                recipeDatabase = Room.databaseBuilder(
                    context,
                    RecipeDatabase::class.java,
                    "recipe.db"
                ).build()
            }
            return recipeDatabase!!
        }
    }

    abstract fun recipeDao():RecipeDao
}