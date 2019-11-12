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
import com.example.myapplication.features.home.data.model.Recipe
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.collections.ArrayList

class PizzaListFragment : Fragment() {

    companion object {
        fun newInstance(data: Bundle? = null): PizzaListFragment {
            val fragment = PizzaListFragment()
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
//        var timeNow: String? = null
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            val current = LocalDateTime.now()
//            val formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy. HH:mm:ss")
//            timeNow = current.format(formatter)
//        } else {
//            val date = Date();
//            val formatter = SimpleDateFormat("MMM dd yyyy HH:mma")
//            timeNow = formatter.format(date)
//        }
//        val recipe = Recipe(
//            image = R.drawable.pizza,
//            title = "Homemade Pizza Margherita",
//            description = "Italian Food snack",
//            time = timeNow,
//            cookTime = "55 mins",
//            level = "medium",
//            ingredients = "Make the base: Put the flour into a large bowl, then stir in the yeast and salt. Make a well, pour in 200ml warm water and the olive oil and bring together with a wooden spoon until you have a soft, fairly wet dough. \n" +
//                    "\n" +
//                    "Make the sauce: Mix the passata, basil and crushed garlic together, then season to taste.\n" +
//                    "\n" +
//                    "Roll out the dough: If you’ve let the dough rise, give it a quick knead, then split into two balls.\n" +
//                    "\n" +
//                    "Top and bake: Heat oven to 240C/fan 220C /gas 8. Put another baking sheet or an upturned baking tray in the oven on the top shelf. Smooth sauce over bases with the back of a spoon.\n" +
//                    "\n" +
//                    "Bake for 8-10 mins until crisp. Serve with a little more olive oil, and basil leaves if using. Repeat step for remaining pizza.\n"
//        )
//        for (x in 0..25) {
//            recipes.add(recipe)
//        }
    }

    private fun setAdapter() {
//        recipesListAdapter = RecipesAdapter(context!!)
//        recipesListAdapter?.setOnItemClickListener(object : RecipesAdapter.ClickListener {
//            override fun onClick(pos: Int, aView: View) {
//                val recipe = recipes[pos]
//                val data = Bundle()
//                data.putSerializable(IntentConstants.RECIPE, recipe)
//                data.putString(Screen.SCREEN, Screen.RECIPE_INFO)
//                Log.d("naaz", recipe.toString())
//                ContainerActivity.start(activity, data)
//            }
//        })
//        recyclerView.adapter = recipesListAdapter
    }



}