package com.shalan.base.activity

import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import android.view.WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.shalan.base.utils.LocaleManager
import com.shalan.base.viewmodel.PlainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import kotlin.reflect.KClass

/**
 * Created by Mohamed Shalan on 4/18/20.
 */

abstract class BaseActivity<ViewModel : PlainViewModel, Binding : ViewDataBinding>(
    override val layoutId: Int,
	clazz: KClass<ViewModel>,
	vararg params: Any = emptyArray()
) :
    AppCompatActivity(), IActivity {

    protected lateinit var binding: Binding
    protected val viewmodel by viewModel(clazz = clazz, parameters = {
    	parametersOf(params)
	})

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, layoutId)
        binding.lifecycleOwner = this
        lifecycle.addObserver(viewmodel)
        viewmodel.startLogic()
        onCreateInit(savedInstanceState)
    }

    fun enableFullScreenMode() {
        window.setFlags(FLAG_LAYOUT_NO_LIMITS, FLAG_LAYOUT_NO_LIMITS)
    }

    fun disableFullScreenMode() {
        window.clearFlags(FLAG_LAYOUT_NO_LIMITS)
    }

    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(LocaleManager.getLocale(newBase))
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        LocaleManager.getLocale(this)
        super.onConfigurationChanged(newConfig)
    }
}
