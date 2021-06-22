package com.shalan.base.fragment.pagedlist

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.MergeAdapter
import androidx.recyclerview.widget.RecyclerView
import com.shalan.base.fragment.BaseFragment
import com.shalan.base.models.IPaginatedModel
import com.shalan.base.recyclerview.PaginatedRecyclerListener
import com.shalan.base.states.CommonStatusImp
import com.shalan.base.viewmodel.pagedlist.BasePagedListViewModel
import kotlin.reflect.KClass

/**
 * Created by Mohamed Shalan on 6/2/20.
 */


abstract class BasePagedListFragment<T : IPaginatedModel, ViewModel : BasePagedListViewModel<T>, Binding : ViewDataBinding>(
	@LayoutRes layoutId: Int,
	clazz: KClass<ViewModel>,
	vararg viewModelParams: Any = emptyArray()
) :
    BaseFragment<Binding, ViewModel>(layoutId, clazz, viewModelParams), IPagedListFragment<T> {

    protected var adapter: MergeAdapter? = null


    override fun onCreateInit(savedInstanceState: Bundle?) {

        observeData()
        observeLoadMoreEvent()

        if (getRecyclerView().layoutManager == null)
            throw RuntimeException("you have to set the layout manager")

        adapter = MergeAdapter(MergeAdapter.Config.Builder().setIsolateViewTypes(false).build())
        getAdapters().forEach { adapter?.addAdapter(it) }

        getRecyclerView().adapter = adapter
		configureRecyclerScrolling()

        getSwipeRefresh()?.setOnRefreshListener {
            viewmodel.refreshData = true
            viewmodel.resetPageNumber()
            viewmodel.startLogic()
            showLoading()
        }
    }

	protected open fun configureRecyclerScrolling() {
		getRecyclerView().addOnScrollListener(object :
			PaginatedRecyclerListener(getRecyclerView().layoutManager as LinearLayoutManager) {

			override fun isLoading(): Boolean =
				viewmodel.dataList_.value?.whichStatus() == CommonStatusImp.LOADING || viewmodel.loadMoreEvent_.value == true

			override fun isLastPage(): Boolean =
				viewmodel.dataList_.value?.fetchData()?.shouldPaginate() == false

			override fun doLoadMore() {
				viewmodel.enableLoadMore()
			}
		})
	}

	override fun getAdapter(): RecyclerView.Adapter<*> = adapter!!

    open fun observeLoadMoreEvent() {
        viewmodel.loadMoreEvent_.observe(viewLifecycleOwner, Observer {
			if (it) {
				disableSwipeToRefreshDuringLoading()
				showLoadMoreLoading()
				viewmodel.getNextPage()
			}
		})
    }


    open fun observeData() {
        viewmodel.dataList_.observe(viewLifecycleOwner, {
			when (it.whichStatus()) {
				CommonStatusImp.LOADING -> {
					disableSwipeToRefreshDuringLoading()
					if (viewmodel.loadMoreEvent_.value != true) showLoading()
				}
				CommonStatusImp.ERROR -> {
					enableSwipeToRefresh()
					hideLoading()
					hideLoadMoreLoading()
					showError(it.fetchError())
				}
				CommonStatusImp.SUCCESS -> {
					enableSwipeToRefresh()
					hideLoading()
					hideLoadMoreLoading()
//					showData(it.fetchData())
				}
			}
		})
    }

    override fun onDestroyView() {
        getRecyclerView().clearOnScrollListeners()
        getRecyclerView().adapter = null
        adapter = null
        super.onDestroyView()
    }
}
