package com.shalan.base.viewmodel

import androidx.lifecycle.LifecycleObserver
import org.koin.core.component.KoinComponent

/**
 * Created by Mohamed Shalan on 3/22/21
 */

interface IPlainViewModel : LifecycleObserver {

    fun startLogic()
}