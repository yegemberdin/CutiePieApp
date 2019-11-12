package com.example.myapplication.features.home.data.repository

import com.example.myapplication.features.home.data.api.ApiService
import com.example.myapplication.features.home.data.model.Recipe
import retrofit2.Response

interface RecipeRepository {
    suspend fun getCupcakes(): Response<List<Recipe>>
}

class RecipeRepositoryImpl(val api: ApiService): RecipeRepository {
    override suspend fun getCupcakes(): Response<List<Recipe>> {
        return api.getCupcakes().await()
    }
}