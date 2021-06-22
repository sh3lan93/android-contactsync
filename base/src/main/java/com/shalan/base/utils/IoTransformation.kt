package com.shalan.base.utils

import com.shalan.base.services.SchedulerService
import io.reactivex.rxjava3.core.*

/**
 * Created by Mohamed Shalan on 6/1/20.
 */

class IoTransformation<T>(
    private val schedulerService: SchedulerService
) :
    ObservableTransformer<T, T>,
    SingleTransformer<T, T>,
    CompletableTransformer, MaybeTransformer<T, T> {

    override fun apply(upstream: Observable<T>): ObservableSource<T> = upstream
        .subscribeOn(schedulerService.ioScheduler)
        .observeOn(schedulerService.mainThreadScheduler)

    override fun apply(upstream: Single<T>): SingleSource<T> = upstream
        .subscribeOn(schedulerService.ioScheduler)
        .observeOn(schedulerService.mainThreadScheduler)

    override fun apply(upstream: Completable): CompletableSource = upstream
        .subscribeOn(schedulerService.ioScheduler)
        .observeOn(schedulerService.mainThreadScheduler)

    override fun apply(upstream: Maybe<T>): MaybeSource<T> = upstream
        .subscribeOn(schedulerService.ioScheduler)
        .observeOn(schedulerService.mainThreadScheduler)
}
