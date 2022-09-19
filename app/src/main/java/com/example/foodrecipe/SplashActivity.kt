package com.example.foodrecipe

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.foodrecipe.database.RecipeDatabase
import com.example.foodrecipe.entities.Category
import com.example.foodrecipe.entities.Meal
import com.example.foodrecipe.interfaces.GetDataService
import com.example.foodrecipe.retrofitclient.RetrofitClientInstance
import kotlinx.android.synthetic.main.activity_splash.*
import kotlinx.coroutines.launch
import pub.devrel.easypermissions.AppSettingsDialog
import pub.devrel.easypermissions.EasyPermissions
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback


class SplashActivity : BaseActivity(), EasyPermissions.RationaleCallbacks,
    EasyPermissions.PermissionCallbacks {
    private var READ_STORAGE_PERM = 123
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        btnGetStarted.setOnClickListener() {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }
    }

    fun getCategories() {
        val service = RetrofitClientInstance.retrofitInstance!!.create(GetDataService::class.java)
        val call = service.getCategoryList()
        call.enqueue(object : retrofit2.Callback<Category> {
            override fun onResponse(
                call: Call<Category>,
                response: Response<Category>
            ) {
                for (arr in response.body()!!.categoryItems!!) getMeal(arr.strCategory)
                insertDataIntoRoomDb(response.body())
            }
            override fun onFailure(call: Call<Category>, t: Throwable) {
                loader.visibility = View.VISIBLE
                Toast.makeText(this@SplashActivity, "something went wrong", Toast.LENGTH_LONG)
                    .show()
            }
        })
    }

    fun getMeal(categoryName: String) {
        val service = RetrofitClientInstance.retrofitInstance!!.create(GetDataService::class.java)
        val call = service.getMealList(categoryName)
        call.enqueue(object : retrofit2.Callback<Meal> {
            override fun onResponse(
                call: Call<Meal>,
                response: Response<Meal>
            ) {
                insertMealDataIntoRoomDb(response.body())
            }

            override fun onFailure(call: Call<Meal>, t: Throwable) {
                loader.visibility = View.VISIBLE
                Toast.makeText(this@SplashActivity, "something went wrong", Toast.LENGTH_LONG)
                    .show()
            }

        })
    }

    private fun clearDataBase() {
        launch {
            this.let {
                RecipeDatabase.getDatabase(this@SplashActivity).recipeDao().clearDb()
            }
        }
    }

    private fun hasReadStoragePermission(): Boolean {
        return EasyPermissions.hasPermissions(
            this,
            android.Manifest.permission.READ_EXTERNAL_STORAGE
        )
    }

    private fun readStorageTask() {
        if (hasReadStoragePermission()) {
            clearDataBase()
            getCategories()
        } else {
            EasyPermissions.requestPermissions(
                this,
                "this app needs access to your storage",
                READ_STORAGE_PERM,
                android.Manifest.permission.READ_EXTERNAL_STORAGE
            )
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this)
    }

    private fun insertDataIntoRoomDb(category: Category?) {
        launch {
            this.let {
                for (arr in category!!.categoryItems!!) {
                    RecipeDatabase.getDatabase(this@SplashActivity)
                        .recipeDao().insertCategory(arr)
                }
            }
            btnGetStarted.visibility = View.VISIBLE
        }
    }

    private fun insertMealDataIntoRoomDb(meal: Meal?) {
        launch {
            this.let {
                for (arr in meal!!.mealsItem!!) {
                    RecipeDatabase.getDatabase(this@SplashActivity)
                        .recipeDao().insertMeal(arr)
                }
            }
            btnGetStarted.visibility = View.VISIBLE
        }
    }

    override fun onRationaleAccepted(requestCode: Int) {
        TODO("Not yet implemented")
    }

    override fun onRationaleDenied(requestCode: Int) {
        TODO("Not yet implemented")
    }

    override fun onPermissionsGranted(requestCode: Int, perms: MutableList<String>) {
        TODO("Not yet implemented")
    }

    override fun onPermissionsDenied(requestCode: Int, perms: MutableList<String>) {
        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
            AppSettingsDialog.Builder(this).build().show()
        }
    }
}