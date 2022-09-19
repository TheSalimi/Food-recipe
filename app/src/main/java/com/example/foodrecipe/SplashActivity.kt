package com.example.foodrecipe

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.foodrecipe.entities.Category
import com.example.foodrecipe.retrofitclient.RetrofitClientInstance
import kotlinx.android.synthetic.main.activity_splash.*
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback


class SplashActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        btnGetStarted.setOnClickListener(){
            val intent = Intent(this,HomeActivity::class.java)
            startActivity(intent)
        }
    }

    fun getCategories(){
        val service = RetrofitClientInstance.retrofitInstance!!.create(GetDataService::class.java)
        val call = service.getCategoryList()
        call.enqueue(object : retrofit2.Callback<List<Category>> {
            override fun onResponse(
                call: Call<List<Category>>,
                response: Response<List<Category>>
            ) {
                insertDataIntoRommDb(response.body())
            }

            override fun onFailure(call: Call<List<Category>>, t: Throwable) {
                loader.visibility = View.VISIBLE
                Toast.makeText(this@SplashActivity,"something went wrong", Toast.LENGTH_LONG).show()
            }

        })
    }

    private fun insertDataIntoRommDb(category: List<Category>?) {

    }
}