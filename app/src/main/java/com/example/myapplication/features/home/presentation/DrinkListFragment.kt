package com.example.myapplication.features.home.presentation

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.common.ContainerActivity
import com.example.myapplication.core.extensions.initRecyclerView
import com.example.myapplication.core.utils.IntentConstants
import com.example.myapplication.core.utils.Screen
import com.example.myapplication.features.home.data.Recipe
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.collections.ArrayList

class DrinkListFragment : Fragment() {

    companion object {
        fun newInstance(data: Bundle? = null): DrinkListFragment {
            val fragment = DrinkListFragment()
            fragment.arguments = data
            return fragment
        }
    }


    private lateinit var recyclerView: RecyclerView
    var recipesListAdapter: RecipesAdapter? = null
    val recipes: ArrayList<Recipe> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_reciepe_lists, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindViews(view)
        setData()
        setAdapter()
    }

    private fun bindViews(view: View) = with(view) {
        recyclerView = findViewById(R.id.recycler_view)
        context.initRecyclerView(recyclerView, true)
    }

    private fun setData() {
        var timeNow: String? = null
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val current = LocalDateTime.now()
            val formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy. HH:mm:ss")
            timeNow = current.format(formatter)
        } else {
            val date = Date();
            val formatter = SimpleDateFormat("MMM dd yyyy HH:mma")
            timeNow = formatter.format(date)
        }
        val recipe = Recipe(
            image = R.drawable.smoothie,
            title = "Lovely Greens Smoothie:",
            description = "Healthy drink type",
            time = timeNow,
            cookTime = "25 mins",
            level = "easy",
            ingredients = "Add ingredients into a blender and blend until smooth and creamy. Add ice depending on temperature preference. \n" +
                    "\n" +
                    "Frozen bananas work best in smoothies. Peel, slice in half, and place in a large Ziploc bag in the freezer overnight.\n" +
                    "\n" +
                    "Add more spinach and kale to increase nutritional benefits.\n")

        for (x in 0..25) {
            recipes.add(recipe)
        }
    }

    private fun setAdapter() {
        recipesListAdapter = RecipesAdapter(recipes,context!!)
        recipesListAdapter?.setOnItemClickListener(object : RecipesAdapter.ClickListener {
            override fun onClick(pos: Int, aView: View) {
                val recipe = recipes[pos]
                val data = Bundle()
                data.putSerializable(IntentConstants.RECIPE, recipe)
                data.putString(Screen.SCREEN, Screen.RECIPE_INFO)
                Log.d("naaz", recipe.toString())
                ContainerActivity.start(activity, data)
            }
        })
        recyclerView.adapter = recipesListAdapter
    }

}