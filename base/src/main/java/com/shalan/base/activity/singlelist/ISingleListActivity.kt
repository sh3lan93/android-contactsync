package com.shalan.base.activity.singlelist

import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout

/**
 * Created by Mohamed Shalan on 21/06/2021
 */

interface ISingleListActivity<Adapter : RecyclerView.Adapter<*>> {

    val recyclerView: RecyclerView

    val adapter: Adapter

    val pullToRefresh: SwipeRefreshLayout?
}