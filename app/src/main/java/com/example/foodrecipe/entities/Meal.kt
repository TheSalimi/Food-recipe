package com.example.foodrecipe.entities
import androidx.room.*
import com.example.foodrecipe.entities.conventer.CategoryListConverter
import com.example.foodrecipe.entities.conventer.MealListConverter
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "meal")
class Meal(
   @PrimaryKey(autoGenerate = true)
   var id:Int ,
    @ColumnInfo(name = "meals")
    @SerializedName("meals")
    @Expose
    @TypeConverters(MealListConverter::class)
    val mealsItem: List<MealsItems>? = null
)

