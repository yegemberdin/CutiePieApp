package com.example.myapplication.features.home.data

import java.io.Serializable

data class Recipe(
    val image: Int,
    val title: String,
    val description: String,
    val time: String,
    val cookTime: String,
    val level: String,
    val inngredients: String
) : Serializable