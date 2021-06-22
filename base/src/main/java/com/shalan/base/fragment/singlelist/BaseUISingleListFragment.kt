package com.shalan.base.fragment.singlelist

import androidx.annotation.LayoutRes
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.shalan.base.viewmodel.singlelist.BaseUISingleListViewModel
import kotlin.reflect.KClass

/**
 * Created by Mohamed Shalan on 15/05/2021
 */

abstract class BaseUISingleListFragment<T, UIModel, ViewModel : BaseUISingleListViewModel<T, UIModel>, DataBinding : ViewDataBinding, Adapter : RecyclerView.Adapter<*>>(
    @LayoutRes layout: Int,
    clazz: KClass<ViewModel>,
    vararg params: Any = emptyArray()
) :
    BaseSingleListFragment<T, ViewModel, DataBinding, Adapter>(
        layout = layout,
        clazz = clazz,
        params = params
    ) {


    override fun showData(data: List<T>?) {
//        data?.let {
//            showUIData(viewmodel.mapToUI(it))
//        }
    }

    abstract fun showUIData(data: UIModel?)
}