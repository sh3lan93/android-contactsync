package com.shalan.contactssync.core

import android.app.Application
import android.provider.ContactsContract
import androidx.work.Constraints
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.shalan.contactssync.BuildConfig
import com.shalan.contactssync.core.di.*
import com.shalan.contactssync.services.ContactsSyncService
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.component.KoinApiExtension
import org.koin.core.context.startKoin
import org.koin.core.logger.EmptyLogger
import java.util.concurrent.TimeUnit

/**
 * Created by Mohamed Shalan on 21/06/2021
 */

class App : Application() {

    @KoinApiExtension
    override fun onCreate() {
        super.onCreate()
        startKoin {
            if (BuildConfig.DEBUG) androidLogger() else EmptyLogger()
            androidContext(this@App)
            modules(
                databaseModule,
                daoModule,
                serviceModules,
                viewmodelModules,
                repoModules,
                useCaseModules
            )
        }
        scheduleSync()
    }

    @KoinApiExtension
    fun scheduleSync() {
        val syncConstrain = Constraints.Builder()
            .apply {
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                    addContentUriTrigger(ContactsContract.Contacts.CONTENT_URI, true)
                    setRequiresBatteryNotLow(true)
                } else {
                    setRequiresBatteryNotLow(true)
                }
            }.build()

        val syncRequest = PeriodicWorkRequestBuilder<ContactsSyncService>(15, TimeUnit.MINUTES)
            .setConstraints(syncConstrain)
            .build()

        WorkManager.getInstance(this).enqueue(syncRequest)
    }
}