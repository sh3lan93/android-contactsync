package com.shalan.base.viewmodel.singlelist

import com.shalan.base.services.SchedulerService
import com.shalan.base.services.SerializationService

/**
 * Created by Mohamed Shalan on 15/05/2021
 */

abstract class BaseUISingleListViewModel<T, UIModel>(
    schedulerService: SchedulerService,
    serializationService: SerializationService,
    ioExceptionMessage: String,
    socketTimeoutExceptionMessage: String,
    internalServerErrorMessage: String
) : BaseSingleListViewModel<T>(
    schedulerService = schedulerService,
    serializationService = serializationService,
    ioExceptionMessage = ioExceptionMessage,
    socketTimeoutExceptionMessage = socketTimeoutExceptionMessage,
    internalServerErrorMessage = internalServerErrorMessage
) {

    abstract fun mapToUI(data: T): UIModel
}