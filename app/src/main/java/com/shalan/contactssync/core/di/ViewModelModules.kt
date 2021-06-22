package com.shalan.contactssync.core.di

import com.shalan.base.services.SchedulerService
import com.shalan.contactssync.features.contactslist.ContactsListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * Created by Mohamed Shalan on 21/06/2021
 */
  

val viewmodelModules = module {
    viewModel {
        ContactsListViewModel(repo = get(), schedulerService = get(), serializationService = get())
    }
}