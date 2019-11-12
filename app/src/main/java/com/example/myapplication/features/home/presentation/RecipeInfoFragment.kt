package com.example.myapplication.features.home.presentation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.myapplication.R
import com.example.myapplication.core.utils.IntentConstants
import com.example.myapplication.features.home.data.model.Recipe
import android.content.Intent
import com.bumptech.glide.Glide
import com.example.myapplication.common.ContainerActivity

class RecipeInfoFragment : Fragment() {

    private lateinit var image: ImageView
    private lateinit var title: TextView
    private lateinit var level: TextView
    private lateinit var cookTime: TextView
    private lateinit var time: TextView
    private lateinit var share: LinearLayout
    private lateinit var ingredients: TextView
    private lateinit var backToolbar: ImageView

    private var recipe: Recipe? = null

    companion object {
        fun newInstance(data: Bundle? = null): RecipeInfoFragment {
            val fragment = RecipeInfoFragment()
            fragment.arguments = data
            return fragment
        }
    }

    override fun setArguments(args: Bundle?) {
        super.setArguments(args)
        recipe = args?.getSerializable(IntentConstants.RECIPE) as Recipe
        Log.d("nazek", recipe.toString())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_recipe_info, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindViews(view)
        setData()
    }

    private fun bindViews(view: View) = with(view){
        image = findViewById(R.id.image)
        title = findViewById(R.id.title)
        level = findViewById(R.id.level)
        cookTime = findViewById(R.id.cookTime)
        share = findViewById(R.id.share)
        time = findViewById(R.id.time)
        ingredients = findViewById(R.id.ingredients)
        backToolbar = findViewById(R.id.backToolbar)

        share.setOnClickListener {
            val sendIntent = Intent()
            sendIntent.action = Intent.ACTION_SEND
            sendIntent.putExtra(
                Intent.EXTRA_TEXT,
                "${title.text} ${ingredients.text}"
            )
            sendIntent.type = "text/plain"
            startActivity(sendIntent)
        }
        backToolbar.setOnClickListener {
            (activity as ContainerActivity).onBackPressed()
        }
    }

    private fun setData() {
        recipe?.let {it ->
            Glide
                .with(context!!)
                .load(it.image)
                .into(image)
            title.text = it.title
            level.text = it.level
            cookTime.text = it.cookTime
            time.text = it.time
            ingredients.text = it.ingredients
        }
    }
}