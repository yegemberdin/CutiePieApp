package com.example.myapplication.features.home.presentation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.example.myapplication.R
import com.google.android.material.tabs.TabLayout
import androidx.fragment.app.FragmentPagerAdapter
import androidx.fragment.app.FragmentManager
import android.widget.TextView
import com.example.myapplication.common.MainFragmentAdapter

class HomeContainerFragment : Fragment() {
    companion object {
        fun newInstance(data: Bundle? = null): HomeContainerFragment {
            val fragment = HomeContainerFragment()
            fragment.arguments = data
            return fragment
        }
    }

    private lateinit var viewPager: ViewPager
    private lateinit var tabLayout: TabLayout
    private var fragmentAdapter: MainFragmentAdapter? = null

    private val tabIcons =
        intArrayOf(
            R.drawable.ic_cupcake,
            R.drawable.ic_pizza_slice,
            R.drawable.ic_coffee,
            R.drawable.ic_donut
        )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home_container, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindViews(view)
        setData()
        setAdapter()
        setupTabIcons()
    }

    private fun bindViews(view: View) = with(view) {
        viewPager = findViewById(R.id.viewpager)
        tabLayout = findViewById(R.id.tabs)

        tabLayout.apply {
            addTab(newTab().setText(getString(R.string.tab_cupcake)))
            addTab(newTab().setText(getString(R.string.tab_pizza)))
            addTab(newTab().setText(getString(R.string.tab_drink)))
            addTab(newTab().setText(getString(R.string.tab_dounut)))

            addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                override fun onTabReselected(p0: TabLayout.Tab?) {
                }

                override fun onTabUnselected(p0: TabLayout.Tab?) {
                }

                override fun onTabSelected(tab: TabLayout.Tab?) {
                    if (tab?.position != null) {
                        Log.d("tab_selected", tab.position.toString())
                        viewPager.currentItem = tab.position
                    } else {
                        Log.d("tab_selected", "null")
                    }
                }
            })
        }
    }

    private fun setData() {

    }

    private fun setAdapter() {
        val list: List<Fragment> = arrayListOf(
            CupCakeListFragment.newInstance(),
            PizzaListFragment.newInstance(),
            DrinkListFragment.newInstance(),
            DounutListFragment.newInstance()
        )
        fragmentAdapter = MainFragmentAdapter(childFragmentManager, list)
        viewPager.apply {
            adapter = fragmentAdapter
            offscreenPageLimit = 2
            addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
                override fun onPageScrollStateChanged(state: Int) {

                }

                override fun onPageScrolled(
                    position: Int,
                    positionOffset: Float,
                    positionOffsetPixels: Int
                ) {
                    //tabLayout.changeTabStyle(selectedTab = tabLayout.getTabAt(position))
                    tabLayout.setScrollPosition(position, positionOffset, true)

                }

                override fun onPageSelected(position: Int) {

                }
            })
        }
    }

    private fun setupTabIcons() {
        tabLayout.getTabAt(0)?.setIcon(tabIcons[0])
        tabLayout.getTabAt(1)?.setIcon(tabIcons[1])
        tabLayout.getTabAt(2)?.setIcon(tabIcons[2])
        tabLayout.getTabAt(3)?.setIcon(tabIcons[3])
    }
}