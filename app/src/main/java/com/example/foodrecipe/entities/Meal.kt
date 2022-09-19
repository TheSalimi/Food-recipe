package com.example.foodrecipe.entities
import androidx.room.ColumnInfo
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.example.foodrecipe.entities.conventer.CategoryListConverter
import com.example.foodrecipe.entities.conventer.MealListConverter
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Meal(
   @PrimaryKey(autoGenerate = true)
   var id:Int ,
    @ColumnInfo(name = "meals")
    @SerializedName("meals")
    @Expose
    @TypeConverters(MealListConverter::class)
    val mealsItem: List<MealsItems>? = null
)

