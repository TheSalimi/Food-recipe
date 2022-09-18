package com.example.foodrecipe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foodrecipe.adapter.MainCategoryAdapter
import com.example.foodrecipe.adapter.SubCategoryAdapter
import com.example.foodrecipe.entities.Recipes
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {
    private var arrMainCategory = ArrayList<Recipes>()
    private var arrSubCategory = ArrayList<Recipes>()

    var mainCategoryAdapter = MainCategoryAdapter()
    var subCategoryAdapter = SubCategoryAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        arrMainCategory.add(Recipes(1,"beef"))
        arrMainCategory.add(Recipes(2,"chicken"))
        arrMainCategory.add(Recipes(3,"dessert"))
        arrMainCategory.add(Recipes(4,"lamb"))
        mainCategoryAdapter.setData(arrMainCategory)

        arrSubCategory.add(Recipes(1,"beef and mustard pie"))
        arrSubCategory.add(Recipes(2,"chicken and mushroom hotpot"))
        arrSubCategory.add(Recipes(3,"banana pancakes"))
        arrSubCategory.add(Recipes(4,"kapsalon"))
        subCategoryAdapter.setData(arrSubCategory)

          rv_main_category.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
          rv_main_category.adapter = mainCategoryAdapter

          rv_sub_category.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
          rv_sub_category.adapter = subCategoryAdapter
    }
}