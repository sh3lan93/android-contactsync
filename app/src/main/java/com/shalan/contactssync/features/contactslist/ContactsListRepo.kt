package com.shalan.contactssync.features.contactslist

import com.shalan.base.repo.BaseRepo
import com.shalan.base.services.SerializationService
import com.shalan.base.services.SessionService
import com.shalan.base.services.SharedService
import com.shalan.contactssync.core.database.ContactsDAO
import com.shalan.contactssync.core.database.models.ContactEntity
import com.shalan.contactssync.core.uimodels.Contact

/**
 * Created by Mohamed Shalan on 21/06/2021
 */

class ContactsListRepo(
    private val dao: ContactsDAO,
    sharedPrefService: SharedService,
    sessionService: SessionService,
    serializationService: SerializationService
) : BaseRepo(
    sharedPrefService = sharedPrefService,
    sessionService = sessionService,
    serializationService = serializationService
) {

    fun loadContacts() = dao.fetchContacts().map(this::mapEntityToUIModel)

    fun loadContactsEntities() = dao.fetchContactsSortedDescending()

    fun insertContacts(contacts: List<ContactEntity>) = dao.insertContacts(contacts)

    fun deleteContacts(contacts: List<ContactEntity>) =
        contacts.map { it.id }.let { dao.deleteContacts(it) }

    fun updateContacts(contacts: List<ContactEntity>) = dao.updateContacts(contacts = contacts)

    private fun mapEntityToUIModel(entities: List<ContactEntity>): List<Contact> = entities.fold(
        mutableListOf(), { acc: MutableList<Contact>, contactEntity: ContactEntity ->
            acc.add(
                Contact(
                    id = contactEntity.id,
                    name = contactEntity.name
                )
            )
            acc
        })
}