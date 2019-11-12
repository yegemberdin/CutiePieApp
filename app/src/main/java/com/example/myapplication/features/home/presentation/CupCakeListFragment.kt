package com.example.myapplication.features.home.presentation

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.common.ContainerActivity
import com.example.myapplication.core.extensions.initRecyclerView
import com.example.myapplication.core.utils.IntentConstants
import com.example.myapplication.core.utils.Screen
import com.example.myapplication.features.home.data.model.Recipe
import org.koin.android.viewmodel.ext.android.viewModel
import java.text.SimpleDateFormat
import kotlin.collections.ArrayList
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


class CupCakeListFragment : Fragment() {

    companion object {
        fun newInstance(data: Bundle? = null): CupCakeListFragment {
            val fragment = CupCakeListFragment()
            fragment.arguments = data
            return fragment
        }
    }

    private lateinit var recyclerView: RecyclerView
    private lateinit var recipesListAdapter: RecipesAdapter

    val homeViewModel: HomeViewModel by viewModel()

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
//            image = R.drawable.lemon_pie,
//            title = "Delicious Lemon Meringues",
//            description = "American Food snack",
//            time = timeNow,
//            cookTime = "35 mins",
//            level = "easy",
//            ingredients = "Preheat oven to 200 degrees. Line two baking sheets with parchment paper or a baking mat.\n" +
//                    "\n" +
//                    "In the bowl of a stand mixer affixed with a whisk attachment, add the egg whites. Beat on medium-low until foamy. Add cream of tartar and salt.\n" +
//                    "\n" +
//                    "Turn speed to high and continue to beat eggs until thick and glossy and stiff peaks form.\n" +
//                    "\n" +
//                    "Gently fold in lemon zest, lemon juice, and vanilla extract with a spatula until just combined. Do not over stir.\n" +
//                    "\n" +
//                    "Carefully spoon mixture in a piping bag fitted with a star tip. Pipe about one teaspoon of mixture for each cookie onto the prepared baking sheet.\n" +
//                    "\n" +
//                    "Bake for about 2 hours or until hardened and no longer soft and sticky. Remove from oven and allow to cool completely.\n" +
//                    "\n" +
//                    "Store in an airtight container."
//        )
//        for (x in 0..25) {
//            recipes.add(recipe)
//        }
        homeViewModel.getCupcakes()
        homeViewModel.liveData.observe(this, Observer  { result ->
            when (result) {
                is HomeViewModel.Result.ShowLoading -> {
                }
                is HomeViewModel.Result.HideLoading -> {
                }
                is HomeViewModel.Result.Cupcakes -> {
                    Log.d("my_dinara_result", result.list.toString())
                    recipesListAdapter.initRecipes(result.list)

                }
                is HomeViewModel.Result.Error -> {
                }
            }
        })
    }

    private fun setAdapter() {
        val listener = object:
            HomeListener {
            override fun onClick(item: Recipe) {
                val data = Bundle()
                data.putSerializable(IntentConstants.RECIPE, item)
                data.putString(Screen.SCREEN, Screen.RECIPE_INFO)
                Log.d("naaz", item.toString())
                ContainerActivity.start(activity, data)
            }

        }
        recipesListAdapter =
            RecipesAdapter(
                listener
            )
//        recipesListAdapter = RecipesAdapter()
//        Log.d("nazekaa", recipes.toString())
//        recipesListAdapter.setOnItemClickListener(object : RecipesAdapter.ClickListener {
//            override fun onClick(pos: Int, aView: View) {
//                val recipe = recipes[pos]
//                val data = Bundle()
//                data.putSerializable(IntentConstants.RECIPE, recipe)
//                data.putString(Screen.SCREEN, Screen.RECIPE_INFO)
//                Log.d("naaz", recipe.toString())
//                ContainerActivity.start(activity, data)
//            }
//        })
        recyclerView.adapter = recipesListAdapter
    }

}