package com.example.myapplication.features.home.data.api

import com.example.myapplication.features.home.data.model.Recipe
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("a80bk")
    fun getCupcakes(): Deferred<Response<List<Recipe>>>
}