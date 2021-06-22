package com.shalan.base.services

/**
 * Created by Mohamed Shalan on 5/31/20.
 */
interface SharedService {

	fun <T> save(key: String, value: T)

	fun <T> get(key: String, defaultValue: T): T

	fun remove(key: String)
}
