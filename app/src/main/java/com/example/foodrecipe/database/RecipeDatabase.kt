package com.example.foodrecipe.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.foodrecipe.dao.RecipeDao
import com.example.foodrecipe.entities.Category
import com.example.foodrecipe.entities.CategoryItems
import com.example.foodrecipe.entities.Recipes
import com.example.foodrecipe.entities.conventer.CategoryListConverter
import java.security.AccessControlContext

@Database(entities = [Recipes::class , CategoryItems::class , Category::class , CategoryListConverter::class] , version = 1 , exportSchema = false)
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