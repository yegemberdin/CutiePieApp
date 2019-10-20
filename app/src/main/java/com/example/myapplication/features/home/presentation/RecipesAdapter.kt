package com.example.myapplication.features.home.presentation

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.features.home.data.Recipe
import kotlinx.android.synthetic.main.recipes_lise_item.view.*

class RecipesAdapter(val recipes : ArrayList<Recipe>, val context: Context) : RecyclerView.Adapter<ViewHolder>() {

    override fun getItemCount(): Int {
        return recipes.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.recipes_lise_item, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.image.setImageResource(recipes.get(position).image)
        holder.title.text = recipes.get(position).title
        holder.description.text = recipes.get(position).description
        holder.cookTime.text = recipes.get(position).cookTime
        holder.level.text = recipes.get(position).level
        holder.time.text = recipes.get(position).time
    }
}

class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {
    val image = view.image
    val title = view.title
    val description = view.description
    val cookTime = view.cookTime
    val level = view.level
    val time = view.time
}