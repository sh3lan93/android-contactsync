package com.shalan.contactssync.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.shalan.contactssync.core.database.models.ContactEntity

/**
 * Created by Mohamed Shalan on 21/06/2021
 */


@Database(entities = arrayOf(ContactEntity::class), version = 4)
abstract class AppDataBase : RoomDatabase() {

    abstract fun contactsDAO(): ContactsDAO
}