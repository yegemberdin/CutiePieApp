package com.example.myapplication.features.home.presentation

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.example.myapplication.features.home.data.model.Recipe
import kotlinx.android.synthetic.main.recipes_lise_item.view.*

class RecipesAdapter(val listener: HomeListener) : RecyclerView.Adapter<RecipesAdapter.ViewHolder>() {


        var list1: ArrayList<Recipe> = ArrayList()

        override fun onCreateViewHolder(parent: ViewGroup, position: Int): ViewHolder {
            return ViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.recipes_lise_item, parent, false)
            )
        }

        override fun getItemCount(): Int =  list1.size

        override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
            viewHolder.bindView(list1.get(position))
            viewHolder.itemView.setOnClickListener {
                listener.onClick(list1.get(position))
            }
        }

        fun initRecipes(list: ArrayList<Recipe>) {
            Log.d("nazerke", list.toString())
            list1.clear()
            list1.addAll(list)
            notifyDataSetChanged()
        }

        class ViewHolder(val view: View): RecyclerView.ViewHolder(view) {

            fun bindView(item: Recipe) = with(view) {
                title.text = item.title
                description.text = item.description
                cookTime.text = item.cookTime
                level.text = item.level
                time.text = item.time
                Glide
                    .with(context)
                    .load(item.image)
                    .into(image)


            }

        }
    }

    interface HomeListener {
        fun onClick(item: Recipe)
    }




