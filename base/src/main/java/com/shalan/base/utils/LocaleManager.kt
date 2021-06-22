package com.shalan.base.utils

import android.content.Context
import android.content.res.Configuration
import com.shalan.base.services.SharedService
import com.shalan.base.services.SharedServiceImp
import java.util.*

/**
 * Created by Mohamed Shalan on 8/10/20.
 */

object LocaleManager {

    const val ARLOCALE = "ar"
    const val ENLOCALE = "en"
    const val LOCALEKEY = "locale"

    private lateinit var sharedPref: SharedService


    fun changeLanguage(context: Context): Context {

        if (!::sharedPref.isInitialized) sharedPref = SharedServiceImp(context)

        val currentLocale = sharedPref.get<String>(LOCALEKEY, ENLOCALE)
        if (currentLocale == ARLOCALE)
            return changeLocaleToEnglish(context)
        else
            return changeLocaleToArabic(context)
    }

    private fun changeLocaleToEnglish(context: Context): Context {
        sharedPref.save(LOCALEKEY, ENLOCALE)
        val locale = Locale(ENLOCALE)
        Locale.setDefault(locale)
        val configuration = Configuration(context.resources.configuration)
        configuration.setLocale(locale)
        return context.createConfigurationContext(configuration)
    }

    private fun changeLocaleToArabic(context: Context): Context {
        sharedPref.save(LOCALEKEY, ARLOCALE)
        val locale = Locale(ARLOCALE)
        Locale.setDefault(locale)
        val configuration = Configuration(context.resources.configuration)
        configuration.setLocale(locale)
        return context.createConfigurationContext(configuration)
    }

    fun getLocale(context: Context): Context {

        if (!::sharedPref.isInitialized) sharedPref = SharedServiceImp(context)

        val language = sharedPref.get(LOCALEKEY, ENLOCALE)
        val locale = Locale(language)
        Locale.setDefault(locale)
        val configuration = Configuration(context.resources.configuration)
        configuration.setLocale(locale)
        return context.createConfigurationContext(configuration)
    }

    fun getCurrentLanguage(context: Context): String {
        if (!::sharedPref.isInitialized) sharedPref = SharedServiceImp(context)
        return sharedPref.get(LOCALEKEY, ENLOCALE)
    }
}
