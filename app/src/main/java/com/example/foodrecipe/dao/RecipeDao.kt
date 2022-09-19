package com.example.foodrecipe.dao

import androidx.room.*
import com.example.foodrecipe.entities.Category
import com.example.foodrecipe.entities.CategoryItems
import com.example.foodrecipe.entities.MealsItems

@Dao
interface RecipeDao {
    @get:Query("SELECT * FROM category ORDER BY id DESC")
    val getAllCategory: List<CategoryItems>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCategory(categoryItems : CategoryItems)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMeal(mealItems : MealsItems)

    @Query("DELETE FROM CategoryItems")
    fun clearDb()
}