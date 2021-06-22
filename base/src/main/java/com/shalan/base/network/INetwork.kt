package com.shalan.base.network

/**
 * Created by Mohamed Shalan on 4/30/21
 */

interface INetwork<T> {

    fun getNetworkClient(): T

    val baseUrl: String
}