package com.shalan.contactssync.core.utils

import android.database.Cursor
import android.provider.ContactsContract
import com.shalan.contactssync.core.database.models.ContactEntity

/**
 * Created by Mohamed Shalan on 22/06/2021
 */

object ContactsCursorUtils {

    fun readContacts(contactsCursor: Cursor): Set<ContactEntity> {
        val contactsSet = mutableSetOf<ContactEntity>()
        if (!contactsCursor.isClosed)
            while (contactsCursor.moveToNext()) {
                ContactEntity(
                    id = contactsCursor.getString(contactsCursor.getColumnIndex(ContactsContract.Contacts._ID))
                        .toLong(),
                    name = contactsCursor.getString(
                        contactsCursor.getColumnIndex(
                            ContactsContract.Contacts.DISPLAY_NAME_PRIMARY
                        )
                    ),
                    updateTime = contactsCursor.getString(
                        contactsCursor.getColumnIndex(
                            ContactsContract.Contacts.CONTACT_LAST_UPDATED_TIMESTAMP
                        )
                    ).toLong()
                ).also {
                    contactsSet.add(it)
                }
            }
        return contactsSet
    }
}