package com.shalan.base.fragment.pagedlist

import androidx.recyclerview.widget.RecyclerView
import com.shalan.base.fragment.singlelist.ISingleListFragment
import com.shalan.base.models.IPaginatedModel

/**
 * Created by Mohamed Shalan on 6/2/20.
 */

interface IPagedListFragment<T : IPaginatedModel> :
    ISingleListFragment<T, RecyclerView.Adapter<*>> {

    fun showLoadMoreLoading()

    fun hideLoadMoreLoading()

    fun getAdapters(): List<RecyclerView.Adapter<*>>

    fun onRefreshDataStarted() {}

}
