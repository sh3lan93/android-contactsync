package com.shalan.base.fragment

import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

/**
 * Created by Mohamed Shalan on 3/22/21
 */

abstract class BasePlainFragment<Binding : ViewDataBinding>(@LayoutRes layout: Int) :
    Fragment(layout), IPlainFragment {

    protected lateinit var binding: Binding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = DataBindingUtil.bind(view)!!
        binding.lifecycleOwner = viewLifecycleOwner
        onCreateInit(savedInstanceState)
    }
}