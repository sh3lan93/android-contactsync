package com.shalan.contactssync.core.di

import com.shalan.base.services.*
import com.shalan.contactssync.core.utils.Constants
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.internal.schedulers.ComputationScheduler
import io.reactivex.rxjava3.internal.schedulers.IoScheduler
import org.koin.android.ext.koin.androidContext
import org.koin.core.qualifier.named
import org.koin.dsl.module

/**
 * Created by Mohamed Shalan on 21/06/2021
 */


val serviceModules = module {

    single<SessionService> {
        SessionServiceImp(
            serializationService = get(),
            sharedService = get()
        )
    }

    single<SchedulerService> {
        SchedulerServiceImp(
            mainThreadScheduler = get(named(Constants.MAIN_THREAD_SCHEDULER)),
            computationalScheduler = get<ComputationScheduler>(),
            ioScheduler = get<IoScheduler>()
        )
    }

    single(named(Constants.MAIN_THREAD_SCHEDULER)) { AndroidSchedulers.mainThread() }

    single { IoScheduler() }

    single { ComputationScheduler() }

    single<SerializationService> { SerializationServiceImp(moshi = get()) }

    single<SharedService> { SharedServiceImp(androidContext()) }

    single<ImageLoadingService> { ImageLoadingServiceImp() }


    single { Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build() }

}