package com.example.myapplication.common

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import com.example.myapplication.R
import com.example.myapplication.common.navigator.FragmentNavigator
import com.example.myapplication.common.navigator.NavigationAnimation
import com.example.myapplication.core.extensions.closeWithAnimation
import com.example.myapplication.core.extensions.closeWithSlideDownAnimation
import com.example.myapplication.core.extensions.openWithAnimation
import com.example.myapplication.core.extensions.openWithSlideUpAnimation
import com.example.myapplication.core.utils.Screen

class ContainerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_container)

        val screen =  intent.getStringExtra(Screen.SCREEN)
        FragmentNavigator.openFragment(this, screen, intent.extras, animation = NavigationAnimation.NONE)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        val animationType = intent.getSerializableExtra("animation")?: NavigationAnimation.SLIDE
        when(animationType) {
            NavigationAnimation.SLIDE -> { closeWithAnimation() }
            NavigationAnimation.SLIDE_UP -> { closeWithSlideDownAnimation() }
            NavigationAnimation.NONE -> {}
        }
    }

    companion object {

        fun start(context: Context?, bundle: Bundle? = null, requestCode: Int? = null,
                  animationType: NavigationAnimation = NavigationAnimation.SLIDE) {
            val intent = Intent(context, ContainerActivity::class.java)
            intent.putExtra("animation", animationType)
            if (bundle != null) {
                intent.putExtras(bundle)
            }
            if (context is Activity) {
                if (requestCode == null) {
                    context.startActivity(intent)
                }
                else {
                    context.startActivityForResult(intent, requestCode)
                }
                when(animationType) {
                    NavigationAnimation.SLIDE -> { context.openWithAnimation() }
                    NavigationAnimation.SLIDE_UP -> { context.openWithSlideUpAnimation() }
                    NavigationAnimation.NONE -> {}
                }
            }
        }

        fun start(fragment: Fragment?, bundle: Bundle? = null, requestCode: Int? = null,
                  animationType: NavigationAnimation = NavigationAnimation.SLIDE) {
            val intent = Intent(fragment?.activity, ContainerActivity::class.java)
            intent.putExtra("animation", animationType)
            if (bundle != null) {
                intent.putExtras(bundle)
            }
            if (requestCode == null) {
                fragment?.startActivity(intent)
            }
            else {
                fragment?.startActivityForResult(intent, requestCode)
            }
            when(animationType) {
                NavigationAnimation.SLIDE -> { fragment?.activity?.openWithAnimation() }
                NavigationAnimation.SLIDE_UP -> { fragment?.activity?.openWithSlideUpAnimation() }
                NavigationAnimation.NONE -> {}
            }
        }
    }

    fun showFragment(fragment: Fragment,
                     tag: String? = null,
                     container: Int = R.id.base_container,
                     animation: NavigationAnimation = NavigationAnimation.NONE
                    ) {
        val transaction = supportFragmentManager.beginTransaction()
        when(animation) {
            NavigationAnimation.SLIDE -> {
                transaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left,  R.anim.enter_from_left, R.anim.exit_to_right)
            }
            NavigationAnimation.SLIDE_UP -> {
                transaction.setCustomAnimations(R.anim.slide_up, R.anim.stay, R.anim.stay, R.anim.slide_down)
            }
            NavigationAnimation.NONE -> {}
        }
        if (tag.isNullOrEmpty()) {
            transaction.replace(container, fragment, tag)
        }
        else {
            transaction.replace(container, fragment, tag)
                .addToBackStack(tag)
        }
        transaction.commit()

    }
}
