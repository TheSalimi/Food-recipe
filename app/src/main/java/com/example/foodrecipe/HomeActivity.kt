package com.example.foodrecipe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foodrecipe.adapter.MainCategoryAdapter
import com.example.foodrecipe.adapter.SubCategoryAdapter
import com.example.foodrecipe.database.RecipeDatabase
import com.example.foodrecipe.entities.CategoryItems
import com.example.foodrecipe.entities.MealsItems
import com.example.foodrecipe.entities.Recipes
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.coroutines.launch

class HomeActivity : BaseActivity() {
    private var arrMainCategory = ArrayList<CategoryItems>()
    private var arrSubCategory = ArrayList<MealsItems>()

    var mainCategoryAdapter = MainCategoryAdapter()
    var subCategoryAdapter = SubCategoryAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

          rv_sub_category.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
          rv_sub_category.adapter = subCategoryAdapter
    }

    private fun getDataFromDb(){
        launch{
            this.let {
                var category = RecipeDatabase.getDatabase(this@HomeActivity).recipeDao().getAllCategory
                arrMainCategory = category as ArrayList<CategoryItems>
                arrMainCategory.reverse()
                mainCategoryAdapter.setData(arrMainCategory)
                rv_main_category.layoutManager = LinearLayoutManager(this@HomeActivity,LinearLayoutManager.HORIZONTAL,false)
                rv_main_category.adapter = mainCategoryAdapter

            }
        }
    }

    private fun getDataFromDb(categoryName:String){
        launch{
            this.let {
                var meals = RecipeDatabase.getDatabase(this@HomeActivity).recipeDao().getSpecificMealList(categoryName)
                arrSubCategory = meals as ArrayList<MealsItems>
                arrSubCategory.reverse()
                subCategoryAdapter.setData(arrSubCategory)
                rv_sub_category.layoutManager = LinearLayoutManager(this@HomeActivity,LinearLayoutManager.HORIZONTAL,false)
                rv_sub_category.adapter = subCategoryAdapter

            }
        }
    }
}