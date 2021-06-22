package com.shalan.base.network.error

import io.reactivex.rxjava3.functions.Consumer

/**
 * Created by Mohamed Shalan on 6/1/20.
 */

interface IErrorHandling : Consumer<Throwable>
