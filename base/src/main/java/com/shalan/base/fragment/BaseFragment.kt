package com.shalan.base.fragment

import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.shalan.base.viewmodel.PlainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import kotlin.reflect.KClass

/**
 * Created by Mohamed Shalan on 3/22/21
 */

abstract class BaseFragment<Binding : ViewDataBinding, ViewModel : PlainViewModel>(
    @LayoutRes layout: Int,
    clazz: KClass<ViewModel>,
    vararg viewModelParams: Any = emptyArray()
) :
    Fragment(layout),
    IPlainFragment {

    protected lateinit var binding: Binding

    protected val viewmodel: ViewModel by viewModel(clazz = clazz, parameters = {
        parametersOf(viewModelParams)
    })

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = DataBindingUtil.bind(view)!!
        binding.lifecycleOwner = viewLifecycleOwner
        viewLifecycleOwner.lifecycle.addObserver(viewmodel)
        onCreateInit(savedInstanceState)
        viewmodel.startLogic()
    }
}