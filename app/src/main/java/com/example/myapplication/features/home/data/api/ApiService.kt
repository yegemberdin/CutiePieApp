package com.example.myapplication.features.home.data.api

import com.example.myapplication.features.home.data.model.Recipe
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("176xne")
    fun getCupcakes(): Deferred<Response<List<Recipe>>>

    @GET("sd8oq")
    fun getDounuts(): Deferred<Response<List<Recipe>>>

    @GET("ubyze")
    fun getDrinks(): Deferred<Response<List<Recipe>>>

    @GET("13x9je")
    fun getPizzas(): Deferred<Response<List<Recipe>>>
}