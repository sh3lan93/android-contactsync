package com.shalan.contactssync.services

import android.content.Context
import android.database.ContentObserver
import android.database.Cursor
import android.provider.ContactsContract
import android.util.Log
import com.shalan.base.services.SchedulerService
import com.shalan.contactssync.core.database.models.ContactEntity
import com.shalan.contactssync.core.usecases.UpdateContactsDatabaseUseCase
import com.shalan.contactssync.core.utils.ContactsCursorUtils
import io.reactivex.rxjava3.core.Single

/**
 * Created by Mohamed Shalan on 21/06/2021
 */

class ContactsObserver(
    private val context: Context,
    private val schedulerService: SchedulerService,
    private val useCase: UpdateContactsDatabaseUseCase
) : ContentObserver(null) {

    companion object {
        val TAG = ContactsObserver::class.java.simpleName
    }

    private val PROJECTION by lazy {
        arrayOf(
            ContactsContract.Contacts._ID,
            ContactsContract.Contacts.DISPLAY_NAME_PRIMARY,
            ContactsContract.Contacts.CONTACT_LAST_UPDATED_TIMESTAMP
        )
    }

    override fun deliverSelfNotifications(): Boolean {
        return true
    }

    override fun onChange(selfChange: Boolean) {
        super.onChange(selfChange)
        Single.create<Cursor> {
            val cursor = context.contentResolver.query(
                ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                PROJECTION,
                null,
                null,
                null
            )
            it.onSuccess(cursor)
        }.map { cursor ->
            val contactsSet = mutableSetOf<ContactEntity>()
            cursor?.let { contactsCursor ->
                contactsSet.addAll(ContactsCursorUtils.readContacts(contactsCursor))
                contactsCursor.close()
            }
            contactsSet
        }.flatMap { contacts ->
            Log.d(TAG, "onChange: calling use case ")
            useCase.execute(contacts.toList())
        }.subscribeOn(schedulerService.ioScheduler)
            .observeOn(schedulerService.mainThreadScheduler)
            .subscribe()
    }
}