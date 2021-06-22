package com.shalan.base.viewmodel.pagedlist

import com.shalan.base.models.IPaginatedModel
import com.shalan.base.services.SchedulerService
import com.shalan.base.services.SerializationService

/**
 * Created by Mohamed Shalan on 07/05/2021
 */

abstract class BaseUIPagedListViewModel<T : IPaginatedModel, UIModel>(
    schedulerService: SchedulerService,
    serializationService: SerializationService,
    ioExceptionMessage: String,
    socketTimeoutExceptionMessage: String,
    internalServerErrorMessage: String
) : BasePagedListViewModel<T>(
    schedulerService = schedulerService,
    serializationService = serializationService,
    ioExceptionMessage = ioExceptionMessage,
    internalServerErrorMessage = internalServerErrorMessage,
    socketTimeoutExceptionMessage = socketTimeoutExceptionMessage
) {

    abstract fun mapToUI(data: T): UIModel

}