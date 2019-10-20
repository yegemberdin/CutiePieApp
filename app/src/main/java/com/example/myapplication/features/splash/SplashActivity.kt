package com.example.myapplication.features.splash

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.common.MainActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MainActivity.start(this)
        finishAffinity()
        overridePendingTransition(0, 0)
    }
}
