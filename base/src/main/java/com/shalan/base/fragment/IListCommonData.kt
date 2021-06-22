package com.shalan.base.fragment

import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout

/**
 * Created by Mohamed Shalan on 11/11/20
 */

interface IListCommonData<T> {

    fun getRecyclerView(): RecyclerView

    fun getSwipeRefresh(): SwipeRefreshLayout?

    fun showLoading()

    fun showError(error: String?)

    fun hideLoading()

    fun showData(data: List<T>?)

    fun showEmptyData()

    fun disableSwipeToRefreshDuringLoading() {
        getSwipeRefresh()?.isRefreshing = false
        getSwipeRefresh()?.isEnabled = false
    }

    fun enableSwipeToRefresh() {
        getSwipeRefresh()?.isEnabled = true
    }

}