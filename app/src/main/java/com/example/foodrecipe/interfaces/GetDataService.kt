package com.example.foodrecipe.interfaces

import com.example.foodrecipe.entities.Category
import com.example.foodrecipe.entities.Meal
import retrofit2.http.GET
import retrofit2.http.Query

interface GetDataService {
    @GET("categories.php")
    fun getCategoryList(): retrofit2.Call<Category>

    @GET("filter.php")
    fun getMealList(@Query("c") category:String): retrofit2.Call<Meal>
}