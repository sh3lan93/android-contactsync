package com.shalan.base.activity.singlelist

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.shalan.base.activity.PlainActivity

/**
 * Created by Mohamed Shalan on 21/06/2021
 */

abstract class BaseSingleListActivity<Binding : ViewDataBinding, Adapter : RecyclerView.Adapter<*>>(
    @LayoutRes layoutId: Int
) :
    PlainActivity<Binding>(layoutId = layoutId), ISingleListActivity<Adapter> {

    override val pullToRefresh: SwipeRefreshLayout?
        get() = null

    override fun onCreateInit(savedInstance: Bundle?) {
        if (recyclerView.layoutManager == null)
            throw RuntimeException("you have to set recycler layout manager")
        recyclerView.adapter = adapter
        pullToRefresh?.setOnRefreshListener {
            onPullToRefresh()
        }
    }

    open fun onPullToRefresh() {

    }

}