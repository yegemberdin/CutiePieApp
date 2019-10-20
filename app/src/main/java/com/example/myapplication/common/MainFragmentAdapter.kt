package com.example.myapplication.common

import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

class MainFragmentAdapter(
    private val fragmentManager: FragmentManager,
    private val fragmentList: List<Fragment>
) : FragmentStatePagerAdapter(fragmentManager) {

    var currentFragment: Fragment? = null
        private set

    override fun getItem(position: Int): Fragment {
        return fragmentList[position]
    }

    override fun getCount(): Int {
        return fragmentList.size
    }

    override fun setPrimaryItem(container: ViewGroup, position: Int, `object`: Any) {
        super.setPrimaryItem(container, position, `object`)
        if (currentFragment !== `object`) {
            currentFragment = `object` as Fragment
        }
    }

    override fun getItemPosition(`object`: Any): Int {
        return super.getItemPosition(`object`)
    }


}
