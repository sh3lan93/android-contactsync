package com.shalan.base.utils

import androidx.lifecycle.MutableLiveData
import com.shalan.base.services.SchedulerService
import com.shalan.base.states.IResult
import io.reactivex.rxjava3.core.*
import com.shalan.base.states.Result

/**
 * Created by Mohamed Shalan on 6/1/20.
 */

class IoLoadingTransformation<T>(
	private val schedulerService: SchedulerService,
	private val result: MutableLiveData<IResult<T>>
) :
	ObservableTransformer<T, T>,
	SingleTransformer<T, T>,
	CompletableTransformer, MaybeTransformer<T, T> {

	override fun apply(upstream: Observable<T>): ObservableSource<T> = upstream
		.doOnSubscribe {
			result.postValue(Result.loading())
		}.subscribeOn(schedulerService.ioScheduler)
		.observeOn(schedulerService.mainThreadScheduler)

	override fun apply(upstream: Single<T>): SingleSource<T> = upstream
		.doOnSubscribe {
			result.postValue(Result.loading())
		}.subscribeOn(schedulerService.ioScheduler)
		.observeOn(schedulerService.ioScheduler)

	override fun apply(upstream: Completable): CompletableSource = upstream
		.doOnSubscribe {
			result.postValue(Result.loading())
		}.subscribeOn(schedulerService.ioScheduler)
		.observeOn(schedulerService.ioScheduler)

	override fun apply(upstream: Maybe<T>): MaybeSource<T> = upstream
		.doOnSubscribe {
			result.postValue(Result.loading())
		}.subscribeOn(schedulerService.ioScheduler)
		.observeOn(schedulerService.ioScheduler)
}
