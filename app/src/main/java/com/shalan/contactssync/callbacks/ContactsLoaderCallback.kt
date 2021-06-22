package com.shalan.contactssync.callbacks

import android.content.Context
import android.database.Cursor
import android.os.Bundle
import android.provider.ContactsContract
import androidx.lifecycle.LifecycleObserver
import androidx.loader.app.LoaderManager
import androidx.loader.content.CursorLoader
import androidx.loader.content.Loader
import com.shalan.contactssync.core.database.models.ContactEntity
import com.shalan.contactssync.core.utils.ContactsCursorUtils

/**
 * Created by Mohamed Shalan on 21/06/2021
 */

class ContactsLoaderCallback(
    private val context: Context,
    private val onDataLoaded: (data: Set<ContactEntity>) -> Unit
) : LoaderManager.LoaderCallbacks<Cursor>, LifecycleObserver {

    private val PROJECTION by lazy {
        arrayOf(
            ContactsContract.Contacts._ID,
            ContactsContract.Contacts.DISPLAY_NAME_PRIMARY,
            ContactsContract.Contacts.CONTACT_LAST_UPDATED_TIMESTAMP
        )
    }

    override fun onCreateLoader(id: Int, args: Bundle?): Loader<Cursor> {
        return CursorLoader(
            context,
            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
            PROJECTION,
            null,
            null,
            null
        )
    }

    override fun onLoadFinished(loader: Loader<Cursor>, data: Cursor?) {
        val contactsSet = mutableSetOf<ContactEntity>()
        data?.let { contactsCursor ->
            contactsSet.addAll(ContactsCursorUtils.readContacts(contactsCursor))
            contactsCursor.close()
        }
        onDataLoaded.invoke(contactsSet)
    }

    override fun onLoaderReset(loader: Loader<Cursor>) {

    }
}