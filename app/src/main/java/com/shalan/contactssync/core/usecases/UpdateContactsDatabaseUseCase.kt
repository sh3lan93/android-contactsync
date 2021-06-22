package com.shalan.contactssync.core.usecases

import android.util.Log
import com.shalan.contactssync.core.database.models.ContactEntity
import com.shalan.contactssync.core.enums.DatabaseOperation
import com.shalan.contactssync.core.uimodels.Contact
import com.shalan.contactssync.features.contactslist.ContactsListRepo
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

/**
 * Created by Mohamed Shalan on 22/06/2021
 */

class UpdateContactsDatabaseUseCase(private val repo: ContactsListRepo) {

    companion object {
        val TAG = UpdateContactsDatabaseUseCase::class.java.simpleName
    }


    fun execute(data: List<ContactEntity>): Single<List<Contact>> =
        repo.loadContactsEntities().map { savedContacts ->
            getContactsToBeUpdated(oldData = savedContacts, newData = data)
        }.flatMapCompletable {
            when (it.first) {
                DatabaseOperation.INSERTION -> insertData(data = it.second)
                DatabaseOperation.DELETION -> deleteData(data = it.second)
                DatabaseOperation.NOTHING -> Completable.complete()
                DatabaseOperation.UPDATE -> updateData(data = it.second)
            }

        }.andThen(repo.loadContacts())

    private fun getContactsToBeUpdated(
        oldData: List<ContactEntity>,
        newData: List<ContactEntity>
    ): Pair<DatabaseOperation, List<ContactEntity>> = when {
        oldData.isEmpty() -> {
            Log.d(
                TAG,
                "getContactsToBeUpdated: no data is available and will write the read contacts "
            )
            Pair(DatabaseOperation.INSERTION, newData)
        }
        oldData.size == newData.size -> {
            Log.d(TAG, "getContactsToBeUpdated: data are equals but will check for any updates ")
            with(calculateDifference(oldData = oldData, newData = newData)) {
                if (this.isEmpty())
                    Pair(
                        com.shalan.contactssync.core.enums.DatabaseOperation.NOTHING, this
                    )
                else
                    Pair(
                        com.shalan.contactssync.core.enums.DatabaseOperation.UPDATE, this
                    )
            }
        }
        newData.size > oldData.size -> {
            Log.d(TAG, "getContactsToBeUpdated: new data found")
            Pair(
                DatabaseOperation.INSERTION,
                calculateDifference(oldData = oldData, newData = newData)
            )
        }
        newData.size < oldData.size -> {
            Log.d(TAG, "getContactsToBeUpdated: data deletion")
            Pair(
                DatabaseOperation.DELETION,
                calculateDifference(oldData = newData, newData = oldData)
            )
        }
        else -> Pair(DatabaseOperation.NOTHING, emptyList())
    }

    private fun insertData(data: List<ContactEntity>) = repo.insertContacts(data)

    private fun deleteData(data: List<ContactEntity>) = repo.deleteContacts(data)

    private fun updateData(data: List<ContactEntity>) = repo.updateContacts(data)

    private fun calculateDifference(oldData: List<ContactEntity>, newData: List<ContactEntity>) =
        newData.sortedByDescending { it.updateTime }.minus(oldData)
}