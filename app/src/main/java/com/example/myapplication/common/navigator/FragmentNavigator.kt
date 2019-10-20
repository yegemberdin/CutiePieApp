package com.example.myapplication.common.navigator

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import com.example.myapplication.R
import com.example.myapplication.common.ContainerActivity
import com.example.myapplication.core.utils.Screen
import com.example.myapplication.features.home.presentation.RecipeInfoFragment
import java.io.Serializable


object FragmentNavigator {

    fun openFragment(
        activity: ContainerActivity,
        screen: String?,
        data: Bundle? = null,
        tag: String? = null,
        animation: NavigationAnimation = NavigationAnimation.SLIDE,
        targetFragment: Fragment? = null,
        requestCode: Int? = null
    ) {
        val fragment = when (screen) {
            Screen.RECIPE_INFO -> {
                RecipeInfoFragment.newInstance(data)
            }
            else -> {
                null
            }
        }

        if (targetFragment != null && requestCode != null) {
            fragment?.setTargetFragment(targetFragment, requestCode)
        }
        activity.showFragment(
            fragment = fragment!!,
            tag = tag,
            container = R.id.base_container,
            animation = animation
        )

    }
}



enum class NavigationAnimation {
    NONE, SLIDE_UP, SLIDE
}
