package com.shalan.base.viewholder

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by Mohamed Shalan on 4/19/20.
 */

abstract class BaseViewHolder<viewBinding : ViewDataBinding, T>(
	protected val binding: viewBinding,
	protected val listener: ItemListener? = null
) :
	RecyclerView.ViewHolder(binding.root) {


	fun baseBinding(item: T){
		binding.root.setOnClickListener {
			listener?.onItemClicked(bindingAdapterPosition)
		}
		bind(item)
		binding.executePendingBindings()
	}

	abstract fun bind(item: T)
}
