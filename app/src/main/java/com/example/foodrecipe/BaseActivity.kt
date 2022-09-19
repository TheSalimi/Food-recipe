package com.example.foodrecipe

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.foodrecipe.entities.Category
import com.example.foodrecipe.retrofitclient.RetrofitClientInstance
import kotlinx.android.synthetic.main.activity_splash.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback
import kotlin.coroutines.CoroutineContext


open class BaseActivity : AppCompatActivity() , CoroutineScope{
    private lateinit var job: Job
    override   val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        job = Job()
    }

    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
    }
}