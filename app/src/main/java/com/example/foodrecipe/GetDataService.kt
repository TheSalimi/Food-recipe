package com.example.foodrecipe

import com.example.foodrecipe.entities.Category
import retrofit2.http.GET

interface GetDataService {
    @GET("/categories.php")
    fun getCategoryList(): retrofit2.Call<List<Category>>
}