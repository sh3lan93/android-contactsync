package com.shalan.base.services


import com.shalan.base.models.IUser

/**
 * Created by Mohamed Shalan on 6/3/20.
 */

interface SessionService {

    fun <T : IUser> saveUserSession(id: Long, user: T, clazz: Class<T>)

    fun <T : IUser> getUser(clazz: Class<T>): T?

    fun saveSessionToken(token: String)

    fun getSessionToken(): String?

    fun setLocale(locale: String)

    fun getLocale(): String

    fun hasValidSession(): Boolean = getSessionToken() != null

    fun clearSession()
}
