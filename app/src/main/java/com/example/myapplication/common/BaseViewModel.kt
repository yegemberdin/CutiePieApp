package com.example.myapplication.common

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

abstract class BaseViewModel : ViewModel(){
    private val myJob = Job()
    protected val uiScope = CoroutineScope(Dispatchers.Main + myJob)

    override fun onCleared() {
        super.onCleared()
        myJob.cancel()
    }
}