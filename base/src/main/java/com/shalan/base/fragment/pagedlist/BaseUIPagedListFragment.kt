package com.shalan.base.fragment.pagedlist

import androidx.annotation.LayoutRes
import androidx.databinding.ViewDataBinding
import com.shalan.base.models.IPaginatedModel
import com.shalan.base.viewmodel.pagedlist.BaseUIPagedListViewModel
import kotlin.reflect.KClass

/**
 * Created by Mohamed Shalan on 10/05/2021
 */

abstract class BaseUIPagedListFragment<T : IPaginatedModel, UIModel, ViewModel : BaseUIPagedListViewModel<T, UIModel>, Binding : ViewDataBinding>(
    @LayoutRes layoutId: Int,
    clazz: KClass<ViewModel>,
    vararg viewModelParams: Any = emptyArray()
) :
    BasePagedListFragment<T, ViewModel, Binding>(
        layoutId = layoutId,
        clazz = clazz,
        viewModelParams = viewModelParams
    ) {

    override fun showData(data: List<T>?) {
//        data?.let {
//            showUIData(viewmodel.mapToUI(it))
//        }
    }

    abstract fun showUIData(data: UIModel?)
}