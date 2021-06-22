package com.shalan.base.services

import io.reactivex.rxjava3.core.Scheduler

/**
 * Created by Mohamed Shalan on 4/18/20.
 */

class SchedulerServiceImp(
    override val mainThreadScheduler: Scheduler,
    override val ioScheduler: Scheduler,
    override val computationalScheduler: Scheduler
) : SchedulerService
