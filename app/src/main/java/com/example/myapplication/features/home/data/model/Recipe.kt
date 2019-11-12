package com.example.myapplication.features.home.data.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Recipe(
    @SerializedName("id") val id : Int,
    @SerializedName("image")val image: String,
    @SerializedName("title")val title: String,
    @SerializedName("description")val description: String,
    @SerializedName("time")val time: String,
    @SerializedName("cookTime")val cookTime: String,
    @SerializedName("level")val level: String,
    @SerializedName("ingredients")val ingredients: String
) : Serializable