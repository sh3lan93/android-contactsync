package com.shalan.contactssync.core.database

import androidx.room.*
import com.shalan.contactssync.core.database.models.ContactEntity
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

/**
 * Created by Mohamed Shalan on 21/06/2021
 */

@Dao
interface ContactsDAO {

    @Query("select * from contacts")
    fun fetchContacts(): Single<List<ContactEntity>>

    @Query("select * from contacts order by last_update_time DESC")
    fun fetchContactsSortedDescending(): Single<List<ContactEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertContacts(contactsList: List<ContactEntity>): Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertContact(contact: ContactEntity): Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertContacts(vararg contact: ContactEntity): Completable

    @Query("delete from contacts where id in (:contactsIds)")
    fun deleteContacts(contactsIds: List<Long>): Completable


    @Update
    fun updateContacts(vararg contacts: ContactEntity): Completable

    @Update
    fun updateContacts(contacts: List<ContactEntity>): Completable


}
