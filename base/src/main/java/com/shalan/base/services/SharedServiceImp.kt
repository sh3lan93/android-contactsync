package com.shalan.base.services

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import androidx.core.content.edit

/**
 * Created by Mohamed Shalan on 5/31/20.
 */

class SharedServiceImp(context: Context) : SharedService {

	private val sharedPreferences: SharedPreferences

	init {
		sharedPreferences = context.getSharedPreferences("SHARED_PREF_RUMBL", MODE_PRIVATE)
	}

	override fun <T> save(key: String, value: T) {
		sharedPreferences.edit(commit = true) {
			when (value) {
				is String -> putString(key, value)
				is Long -> putLong(key, value)
				is Int -> putInt(key, value)
				is Float -> putFloat(key, value)
				is Boolean -> putBoolean(key, value)
			}
		}
	}

	@Suppress("UNCHECKED_CAST")
	override fun <T> get(key: String, defaultValue: T): T = when (defaultValue) {
		is String -> sharedPreferences.getString(key, defaultValue)
		is Long -> sharedPreferences.getLong(key, defaultValue)
		is Int -> sharedPreferences.getInt(key, defaultValue)
		is Float -> sharedPreferences.getFloat(key, defaultValue)
		is Boolean -> sharedPreferences.getBoolean(key, defaultValue)
		else -> throw RuntimeException("unsupported type")
	} as T

	override fun remove(key: String) {
		sharedPreferences.edit {
			remove(key)
		}
	}

}
