package com.shalan.contactssync.services

import android.content.Context
import android.provider.ContactsContract
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.shalan.contactssync.core.usecases.UpdateContactsDatabaseUseCase
import com.shalan.contactssync.core.utils.ContactsCursorUtils
import org.koin.core.component.KoinApiExtension
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

/**
 * Created by Mohamed Shalan on 22/06/2021
 */

@KoinApiExtension
class ContactsSyncService(context: Context, workerParameters: WorkerParameters) :
    Worker(context, workerParameters), KoinComponent {

    private val useCase: UpdateContactsDatabaseUseCase by inject()

    private val PROJECTION by lazy {
        arrayOf(
            ContactsContract.Contacts._ID,
            ContactsContract.Contacts.DISPLAY_NAME_PRIMARY,
            ContactsContract.Contacts.CONTACT_LAST_UPDATED_TIMESTAMP
        )
    }

    override fun doWork(): Result {
        val cursor = applicationContext.contentResolver.query(
            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
            PROJECTION,
            null,
            null,
            null
        )
        cursor?.let { contactsCursor ->
            val contactsSet = ContactsCursorUtils.readContacts(contactsCursor)
            Log.d(ContactsSyncService::class.java.simpleName, "doWork: ${contactsSet.size}")
            contactsCursor.close()
            useCase.execute(contactsSet.toList()).blockingGet()
        }
        return Result.success()
    }

}