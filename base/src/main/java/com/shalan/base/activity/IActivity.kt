package com.shalan.base.activity

import android.os.Bundle
import androidx.fragment.app.Fragment

/**
 * Created by Mohamed Shalan on 4/18/20.
 */

interface IActivity {

	val layoutId: Int

	fun onCreateInit(savedInstance: Bundle?)

}
