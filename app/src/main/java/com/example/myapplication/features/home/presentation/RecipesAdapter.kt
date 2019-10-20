package com.example.myapplication.features.home.presentation

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.features.home.data.Recipe
import kotlinx.android.synthetic.main.recipes_lise_item.view.*

class RecipesAdapter(val recipes : ArrayList<Recipe>, val context: Context) : RecyclerView.Adapter<RecipesAdapter.ViewHolder>() {

    lateinit var mClickListener: ClickListener

    fun setOnItemClickListener(aClickListener: ClickListener) {
        mClickListener = aClickListener
    }


    override fun getItemCount(): Int {
        return recipes.size
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.recipes_lise_item,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.image.setImageResource(recipes.get(position).image)
        holder.title.text = recipes.get(position).title
        holder.description.text = recipes.get(position).description
        holder.cookTime.text = recipes.get(position).cookTime
        holder.level.text = recipes.get(position).level
        holder.time.text = recipes.get(position).time
    }


   inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {
        val image: ImageView = view.image
        val title: TextView = view.title
        val description: TextView = view.description
        val cookTime: TextView = view.cookTime
        val level: TextView = view.level
        val time: TextView = view.time

        override fun onClick(p0: View?) {
            mClickListener.onClick(adapterPosition, p0!!)
        }

        init {
            image.setOnClickListener(this)
        }

    }

    interface ClickListener {
        fun onClick(pos: Int, aView: View)
    }
}

