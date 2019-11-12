package com.example.myapplication

import android.app.Application
import com.example.myapplication.core.di.appModules
import org.koin.android.ext.android.startKoin

class ApplicationClass : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin(this, appModules)
    }
}