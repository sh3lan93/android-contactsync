package com.shalan.contactssync.core.database.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by Mohamed Shalan on 21/06/2021
 */

@Entity(tableName = "contacts")
data class ContactEntity(
    @PrimaryKey val id: Long,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "last_update_time") val updateTime: Long
) {
}
