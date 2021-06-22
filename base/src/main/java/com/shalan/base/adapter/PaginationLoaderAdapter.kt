package com.shalan.base.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.rumbl.bases.viewholders.PaginationLoaderViewHolder
import com.shalan.base.R
import com.shalan.base.databinding.PaginationLoaderLayoutBinding
import com.shalan.base.states.CommonStatusImp
import com.shalan.base.states.ICommonStatus

/**
 * Created by Mohamed Shalan on 6/5/20.
 */

class PaginationLoaderAdapter : RecyclerView.Adapter<PaginationLoaderViewHolder>() {


	var state: ICommonStatus = CommonStatusImp.SUCCESS
		set(value) {
			if (field != value) {
				val isOldStatusLoading = showLoaderAsItem(field)
				val isNewStatusLoading = showLoaderAsItem(value)

				if (isOldStatusLoading && !isNewStatusLoading)
					notifyItemRemoved(0)
				else if (isOldStatusLoading && isNewStatusLoading)
					notifyItemChanged(0)
				else if (!isOldStatusLoading && isNewStatusLoading)
					notifyItemInserted(0)

				field = value
			}
		}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PaginationLoaderViewHolder =
		DataBindingUtil.inflate<PaginationLoaderLayoutBinding>(
			LayoutInflater.from(parent.context), viewType, parent, false
		).let {
			PaginationLoaderViewHolder(it)
		}

	override fun getItemViewType(position: Int): Int = R.layout.pagination_loader_layout

	override fun getItemCount(): Int = if (showLoaderAsItem(state)) 1 else 0

	override fun onBindViewHolder(holder: PaginationLoaderViewHolder, position: Int) {
		holder.bind()
	}

	private fun showLoaderAsItem(state: ICommonStatus) = state == CommonStatusImp.LOADING
}
