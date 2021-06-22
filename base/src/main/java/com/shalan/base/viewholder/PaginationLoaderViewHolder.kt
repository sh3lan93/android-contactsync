package com.rumbl.bases.viewholders

import androidx.recyclerview.widget.RecyclerView
import com.shalan.base.databinding.PaginationLoaderLayoutBinding


/**
 * Created by Mohamed Shalan on 6/5/20.
 */

class PaginationLoaderViewHolder(private val binding: PaginationLoaderLayoutBinding) :
	RecyclerView.ViewHolder(binding.root) {

	fun bind() {
		binding.executePendingBindings()
	}
}
