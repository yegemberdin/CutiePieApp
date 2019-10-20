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

class DounutListFragment : Fragment() {

    companion object {
        fun newInstance(data: Bundle? = null): DounutListFragment {
            val fragment = DounutListFragment()
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
            image = R.drawable.donuts,
            title = "Woman's Glazed Donuts",
            description = "Perfect Yeast Doughnut",
            time = timeNow,
            cookTime = "45 mins",
            level = "medium",
            ingredients = "To make the dough: warm the milk until it is getting nice and warm when you dip your finger in it (about 105 degrees).  \n" +
                    "\n" +
                    "Add the beaten eggs and melted butter to the bowl and stir to combine.\n" +
                    "\n" +
                    "To form the donuts: Remove the dough from the fridge and roll it out on a lightly floured surface until it is 1/2 to 1/3 of an inch thick. Use a three-inch donut cutter to cut out the donuts.\n")

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