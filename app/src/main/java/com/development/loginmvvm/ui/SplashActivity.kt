package com.development.loginmvvm.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.development.loginmvvm.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashActivity:AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        lifecycleScope.launch {
            delay(2000)
            Intent(this@SplashActivity, MainActivity::class.java).let {
                startActivity(it)
                finish()
            }
        }
    }

}