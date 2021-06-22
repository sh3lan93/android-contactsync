package com.shalan.base.fragment.singlelist

import androidx.recyclerview.widget.RecyclerView
import com.shalan.base.fragment.IListCommonData

/**
 * Created by Mohamed Shalan on 6/10/20.
 */

interface ISingleListFragment<T, Adapter : RecyclerView.Adapter<*>> : IListCommonData<T> {

    fun getAdapter(): Adapter
}
