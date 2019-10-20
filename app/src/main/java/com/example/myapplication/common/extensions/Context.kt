package com.example.myapplication.common.extensions

import android.content.Context
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

fun Context.initRecyclerView(recyclerView: RecyclerView, withDivider: Boolean = false, vertical: Boolean = true) = with(recyclerView) {
    val orientation = if (vertical) {
        LinearLayoutManager.VERTICAL
    } else {
        LinearLayoutManager.HORIZONTAL
    }
    val linearLayoutManager = LinearLayoutManager(this@initRecyclerView, orientation, false)
    layoutManager = linearLayoutManager
    setHasFixedSize(true)
    setItemViewCacheSize(20)
    if (withDivider) {
        val dividerItemDecoration = DividerItemDecoration(this@initRecyclerView, linearLayoutManager.orientation)
        recyclerView.addItemDecoration(dividerItemDecoration)
    }
}