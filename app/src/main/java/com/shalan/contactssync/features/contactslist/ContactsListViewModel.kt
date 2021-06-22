package com.shalan.contactssync.features.contactslist

import com.shalan.base.services.SchedulerService
import com.shalan.base.services.SerializationService
import com.shalan.base.viewmodel.singlelist.BaseSingleListViewModel
import com.shalan.contactssync.core.database.models.ContactEntity
import com.shalan.contactssync.core.uimodels.Contact
import com.shalan.contactssync.core.usecases.UpdateContactsDatabaseUseCase
import io.reactivex.rxjava3.core.Single

/**
 * Created by Mohamed Shalan on 21/06/2021
 */

class ContactsListViewModel(
    private val repo: ContactsListRepo,
    schedulerService: SchedulerService,
    serializationService: SerializationService
) : BaseSingleListViewModel<Contact>(
    schedulerService = schedulerService,
    serializationService = serializationService
) {

    override fun loadData(): Single<List<Contact>> = repo.loadContacts()

    fun updateSavedContactsList(data: List<ContactEntity>) {
        UpdateContactsDatabaseUseCase(repo = repo).execute(data).execute(dataResult)
    }
}