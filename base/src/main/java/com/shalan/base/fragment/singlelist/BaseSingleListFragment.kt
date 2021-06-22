package com.shalan.base.fragment.singlelist

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.shalan.base.fragment.BaseFragment
import com.shalan.base.states.CommonStatusImp
import com.shalan.base.viewmodel.singlelist.BaseSingleListViewModel
import kotlin.reflect.KClass

/**
 * Created by Mohamed Shalan on 6/10/20.
 */


abstract class BaseSingleListFragment<T, ViewModel : BaseSingleListViewModel<T>, DataBinding : ViewDataBinding, Adapter : RecyclerView.Adapter<*>>(
    @LayoutRes layout: Int,
    clazz: KClass<ViewModel>, vararg params: Any = emptyArray()
) : BaseFragment<DataBinding, ViewModel>(layout, clazz, params), ISingleListFragment<T, Adapter> {


    override fun onCreateInit(savedInstanceState: Bundle?) {
        if (getRecyclerView().layoutManager == null)
            throw RuntimeException("you have to set the layout manager")

        observeData()
        getRecyclerView().adapter = getAdapter()

        getSwipeRefresh()?.setOnRefreshListener {
            viewmodel.refreshData = true
            viewmodel.startLogic()
            showLoading()
        }
    }

    open fun observeData() {
        viewmodel.data_.observe(viewLifecycleOwner, Observer {
            when (it.whichStatus()) {
                CommonStatusImp.LOADING -> {
                    disableSwipeToRefreshDuringLoading()
                    showLoading()
                }
                CommonStatusImp.SUCCESS -> {
                    enableSwipeToRefresh()
                    hideLoading()
                    if (it.fetchData()?.isEmpty() == false)
                        showData(it.fetchData())
                    else
                        showEmptyData()
                }
                CommonStatusImp.ERROR -> {
                    enableSwipeToRefresh()
                    hideLoading()
                    showError(it.fetchError())
                }
            }
        })
    }

    override fun onDestroyView() {
        getRecyclerView().adapter = null
        super.onDestroyView()
    }

}
