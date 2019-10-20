package com.example.myapplication.common

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import com.example.myapplication.R
import com.example.myapplication.features.home.presentation.HomeContainerFragment

class MainActivity : AppCompatActivity() {

    companion object {
        fun start(context: Context?, bundle: Bundle? = null) {
            val intent = Intent(context, MainActivity::class.java)
            if (bundle != null) {
                intent.putExtras(bundle)
            }
            (context as Activity).startActivity(intent)
        }
    }

    private val homeFragment = HomeContainerFragment.newInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            changeFragment(homeFragment)
        }
    }

    private fun changeFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.menuContainer, fragment).commit()
    }
}

