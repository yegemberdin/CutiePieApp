package com.example.myapplication.features.home.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.myapplication.R

class DounutListFragment : Fragment() {

    companion object {
        fun newInstance(data: Bundle? = null): DounutListFragment {
            val fragment = DounutListFragment()
            fragment.arguments = data
            return fragment
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.custom_search, container, false)
    }

}