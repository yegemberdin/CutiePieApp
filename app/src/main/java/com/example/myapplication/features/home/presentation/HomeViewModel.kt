package com.example.myapplication.features.home.presentation

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.myapplication.common.BaseViewModel
import com.example.myapplication.features.home.data.model.Recipe
import com.example.myapplication.features.home.data.repository.RecipeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeViewModel(val repository: RecipeRepository): BaseViewModel() {
    val liveData = MutableLiveData<Result>()

    fun getCupcakes() {
        liveData.value = Result.ShowLoading
        uiScope.launch {
            try {
                val response = withContext(Dispatchers.IO) {
                    repository.getCupcakes()
                }

                liveData.value = Result.HideLoading
                if (response.isSuccessful) {
                    response.body()?.let { list ->
                        liveData.value = Result.Cupcakes(list as ArrayList<Recipe>)

                    }

                } else {
                    val error = response.errorBody()?.string()
                    Log.d("my_response", error)
                }
            } catch (e: Exception) {
                e.printStackTrace()
                liveData.value =
                    Result.Error(e.localizedMessage ?: "error")
                liveData.value = Result.HideLoading
            }
        }
    }

    fun getDounuts() {
        liveData.value = Result.ShowLoading
        uiScope.launch {
            try {
                val response = withContext(Dispatchers.IO) {
                    repository.getDounuts()
                }

                liveData.value = Result.HideLoading
                if (response.isSuccessful) {
                    response.body()?.let { list ->
                        liveData.value = Result.Dounuts(list as ArrayList<Recipe>)

                    }

                } else {
                    val error = response.errorBody()?.string()
                    Log.d("my_response", error)
                }
            } catch (e: Exception) {
                e.printStackTrace()
                liveData.value =
                    Result.Error(e.localizedMessage ?: "error")
                liveData.value = Result.HideLoading
            }
        }
    }

    fun getDrinks() {
        liveData.value = Result.ShowLoading
        uiScope.launch {
            try {
                val response = withContext(Dispatchers.IO) {
                    repository.getDrinks()
                }

                liveData.value = Result.HideLoading
                if (response.isSuccessful) {
                    response.body()?.let { list ->
                        liveData.value = Result.Drinks(list as ArrayList<Recipe>)

                    }

                } else {
                    val error = response.errorBody()?.string()
                    Log.d("my_response", error)
                }
            } catch (e: Exception) {
                e.printStackTrace()
                liveData.value =
                    Result.Error(e.localizedMessage ?: "error")
                liveData.value = Result.HideLoading
            }
        }
    }

    fun getPizzas() {
        liveData.value = Result.ShowLoading
        uiScope.launch {
            try {
                val response = withContext(Dispatchers.IO) {
                    repository.getPizzas()
                }

                liveData.value = Result.HideLoading
                if (response.isSuccessful) {
                    response.body()?.let { list ->
                        liveData.value = Result.Pizzas(list as ArrayList<Recipe>)

                    }

                } else {
                    val error = response.errorBody()?.string()
                    Log.d("my_response", error)
                }
            } catch (e: Exception) {
                e.printStackTrace()
                liveData.value =
                    Result.Error(e.localizedMessage ?: "error")
                liveData.value = Result.HideLoading
            }
        }
    }

    sealed class Result {
        object ShowLoading : Result()
        object HideLoading : Result()
        data class Cupcakes(val list: ArrayList<Recipe>) : Result()
        data class Dounuts(val list: ArrayList<Recipe>) : Result()
        data class Drinks(val list: ArrayList<Recipe>) : Result()
        data class Pizzas(val list: ArrayList<Recipe>) : Result()
        data class Error(val error: String) : Result()
    }
}