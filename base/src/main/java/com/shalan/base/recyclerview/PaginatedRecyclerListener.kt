package com.shalan.base.recyclerview

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by Mohamed Shalan on 6/3/20.
 */

abstract class PaginatedRecyclerListener(private val layoutManager: LinearLayoutManager) :
	RecyclerView.OnScrollListener() {

	companion object {
		val PAGE_SIZE = 10
	}

	override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
		val visibleItemCount = layoutManager.childCount
		val totalItemCount = layoutManager.itemCount
		val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()

		if (!isLoading() && !isLastPage()) {
			if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount
				&& firstVisibleItemPosition >= 0
				&& totalItemCount >= PAGE_SIZE
			) {
				doLoadMore()
			}
		}
	}

	abstract fun isLoading(): Boolean

	abstract fun isLastPage(): Boolean

	abstract fun doLoadMore()
}
