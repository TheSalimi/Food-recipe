package com.example.foodrecipe.dao

import androidx.room.*
import com.example.foodrecipe.entities.Category
import com.example.foodrecipe.entities.CategoryItems
import com.example.foodrecipe.entities.MealsItems

@Dao
interface RecipeDao {
    @Query("SELECT * FROM CategoryItems ORDER BY id DESC")
    suspend fun getAllCategory() : List<CategoryItems>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCategory(categoryItems: CategoryItems?)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMeal(mealsItems: MealsItems?)

    @Query("DELETE FROM categoryitems")
    suspend fun clearDb()

    @Query("SELECT * FROM MealItems WHERE categoryName = :categoryName ORDER BY id DESC")
    suspend fun getSpecificMealList(categoryName:String) : List<MealsItems>
}