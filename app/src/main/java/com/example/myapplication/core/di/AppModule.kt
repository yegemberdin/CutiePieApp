package com.example.myapplication.core.di

import android.util.Log
import com.example.myapplication.BuildConfig
import com.example.myapplication.features.home.data.api.ApiService
import com.example.myapplication.features.home.data.repository.RecipeRepository
import com.example.myapplication.features.home.data.repository.RecipeRepositoryImpl
import com.example.myapplication.features.home.presentation.HomeViewModel
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val networkModule = module {

    single { createHttpClient()}
    single { createApiService(get())}
    single { RecipeRepositoryImpl(get()) as RecipeRepository }

}

val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
}

fun createHttpClient(): OkHttpClient {
    val interceptor = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger { message -> Log.d("OkHttp", message) })
    interceptor.level = HttpLoggingInterceptor.Level.BODY

    val okHttpClient = OkHttpClient.Builder()
        .connectTimeout(60L, TimeUnit.SECONDS)
        .readTimeout(60L, TimeUnit.SECONDS)

    if (BuildConfig.DEBUG) {
        okHttpClient
            .addInterceptor(interceptor)
    }
    return okHttpClient.build()
}



fun createApiService(okHttpClient: OkHttpClient):ApiService {
    return Retrofit.Builder()
        .baseUrl(BuildConfig.SHOP_API)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
        .addCallAdapterFactory(CoroutineCallAdapterFactory.invoke())
        .build()
        .create(ApiService::class.java)
}


val appModules = listOf(networkModule, viewModelModule)