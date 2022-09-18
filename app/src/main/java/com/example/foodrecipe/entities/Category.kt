package com.example.foodrecipe.entities

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.example.foodrecipe.entities.conventer.CategoryListConverter
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Category(
    @PrimaryKey(autoGenerate = true)
    var id:Int,

    @ColumnInfo(name = "categoryItems")
    @Expose
    @SerializedName("categories")
    @TypeConverters(CategoryListConverter::class)
    val categoryItems: List<CategoryItems>? = null
)
