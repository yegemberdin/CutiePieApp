package com.example.myapplication.features.home.presentation

import android.os.Bundle
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


class DounutListFragment : Fragment() {

    companion object {
        fun newInstance(data: Bundle? = null): DounutListFragment {
            val fragment = DounutListFragment()
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
        homeViewModel.getDounuts()
        homeViewModel.liveData.observe(this, Observer  { result ->
            when (result) {
                is HomeViewModel.Result.ShowLoading -> {
                }
                is HomeViewModel.Result.HideLoading -> {
                }
                is HomeViewModel.Result.Dounuts -> {
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
                ContainerActivity.start(activity, data)
            }

        }
        recipesListAdapter =
            RecipesAdapter(
                listener
            )
        recyclerView.adapter = recipesListAdapter
    }

}