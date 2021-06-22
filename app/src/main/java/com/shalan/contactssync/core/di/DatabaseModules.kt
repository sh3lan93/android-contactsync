package com.shalan.contactssync.core.di

import androidx.room.Room
import com.shalan.contactssync.core.database.AppDataBase
import com.shalan.contactssync.core.utils.Constants
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

/**
 * Created by Mohamed Shalan on 21/06/2021
 */


val databaseModule = module {
    single {
        Room.databaseBuilder(
            androidContext(),
            AppDataBase::class.java,
            Constants.DATABASE_NAME
        ).fallbackToDestructiveMigration().build()
    }
}

val daoModule = module {
    factory {
        get<AppDataBase>().contactsDAO()
    }
}