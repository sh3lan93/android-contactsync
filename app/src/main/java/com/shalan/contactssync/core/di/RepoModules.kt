package com.shalan.contactssync.core.di

import com.shalan.contactssync.features.contactslist.ContactsListRepo
import org.koin.dsl.module

/**
 * Created by Mohamed Shalan on 21/06/2021
 */


val repoModules = module {
    factory {
        ContactsListRepo(
            dao = get(),
            serializationService = get(),
            sessionService = get(),
            sharedPrefService = get()
        )
    }
}