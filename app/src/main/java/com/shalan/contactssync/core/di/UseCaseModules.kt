package com.shalan.contactssync.core.di

import com.shalan.contactssync.core.usecases.UpdateContactsDatabaseUseCase
import org.koin.dsl.module

/**
 * Created by Mohamed Shalan on 22/06/2021
 */

val useCaseModules = module {
    factory { UpdateContactsDatabaseUseCase(repo = get()) }
}