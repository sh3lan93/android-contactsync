package com.shalan.base.activity

import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.shalan.base.utils.LocaleManager

/**
 * Created by Mohamed Shalan on 3/23/21
 */

abstract class PlainActivity<Binding : ViewDataBinding>(override val layoutId: Int) :
    AppCompatActivity(), IActivity {

    protected lateinit var binding: Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, layoutId)
        binding.lifecycleOwner = this
        onCreateInit(savedInstanceState)
    }

    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(LocaleManager.getLocale(newBase))
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        LocaleManager.getLocale(this)
        super.onConfigurationChanged(newConfig)
    }

}