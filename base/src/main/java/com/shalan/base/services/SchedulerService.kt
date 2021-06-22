package com.shalan.base.services

import io.reactivex.rxjava3.core.Scheduler

/**
 * Created by Mohamed Shalan on 4/18/20.
 */

interface SchedulerService {

	val mainThreadScheduler: Scheduler

	val ioScheduler: Scheduler

	val computationalScheduler: Scheduler
}
