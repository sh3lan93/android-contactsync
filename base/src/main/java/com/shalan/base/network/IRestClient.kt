package com.shalan.base.network

import okhttp3.Interceptor
import okhttp3.OkHttpClient

/**
 * Created by Mohamed Shalan on 4/30/21
 */

interface IRestClient<T>: INetwork<T> {

    fun getOkHttpClient(): OkHttpClient

    fun getAdditionalHeaders(): Map<String, String>? = null

    fun getAdditionalInterceptors(): List<Interceptor>? = null

}