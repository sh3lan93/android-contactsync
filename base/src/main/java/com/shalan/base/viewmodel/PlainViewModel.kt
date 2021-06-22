package com.shalan.base.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.shalan.base.network.error.IErrorHandling
import com.shalan.base.network.error.NetworkErrorHandlingImp
import com.shalan.base.services.SchedulerService
import com.shalan.base.services.SerializationService
import com.shalan.base.states.IResult
import com.shalan.base.states.Result
import com.shalan.base.utils.IoLoadingTransformation
import com.shalan.base.utils.IoTransformation
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.addTo

/**
 * Created by Mohamed Shalan on 3/22/21
 */

open class PlainViewModel(
    protected val schedulerService: SchedulerService,
    protected val serializationService: SerializationService,
    protected val ioExceptionMessage: String,
    protected val socketTimeoutExceptionMessage: String,
    protected val internalServerErrorMessage: String
) : ViewModel(),
    IPlainViewModel {

    protected val compositeDisposable by lazy {
        CompositeDisposable()
    }

    override fun startLogic() {

    }

    fun <T> getIOLoadingTransformers(result: MutableLiveData<IResult<T>>) =
        IoLoadingTransformation(schedulerService, result)


    protected fun <T> getIo(): IoTransformation<T> = IoTransformation(schedulerService)

    fun <T> Observable<T>.execute(result: MutableLiveData<IResult<T>>) {
        this.compose(getIOLoadingTransformers(result))
            .subscribe(
                {
                    result.postValue(Result.success(it))
                },
                NetworkErrorHandlingImp(
                    result = result,
                    ioExceptionMessage = ioExceptionMessage,
                    socketExceptionMessage = socketTimeoutExceptionMessage,
                    internalServerErrorExceptionMessage = internalServerErrorMessage,
                    serializationService = serializationService
                )
            ).addTo(compositeDisposable)
    }

    fun <T> Single<T>.execute(result: MutableLiveData<IResult<T>>) {
        this.compose(getIOLoadingTransformers(result))
            .subscribe(
                {
                    result.postValue(Result.success(it))
                }, NetworkErrorHandlingImp(
                    result = result,
                    ioExceptionMessage = ioExceptionMessage,
                    socketExceptionMessage = socketTimeoutExceptionMessage,
                    internalServerErrorExceptionMessage = internalServerErrorMessage,
                    serializationService = serializationService
                )
            ).addTo(compositeDisposable)
    }

    fun <T> Maybe<T>.execute(
        result: MutableLiveData<IResult<T>>
    ) {
        this.compose(getIOLoadingTransformers(result))
            .subscribe(
                {
                    result.postValue(Result.success(it))
                }, NetworkErrorHandlingImp(
                    result = result,
                    ioExceptionMessage = ioExceptionMessage,
                    socketExceptionMessage = socketTimeoutExceptionMessage,
                    internalServerErrorExceptionMessage = internalServerErrorMessage,
                    serializationService = serializationService
                )
            ).addTo(compositeDisposable)
    }

    fun Completable.execute(
        result: MutableLiveData<IResult<Any>>
    ) {
        this.compose(getIOLoadingTransformers(result))
            .subscribe(
                {
                    result.postValue(Result.success())
                }, NetworkErrorHandlingImp(
                    result = result,
                    ioExceptionMessage = ioExceptionMessage,
                    socketExceptionMessage = socketTimeoutExceptionMessage,
                    internalServerErrorExceptionMessage = internalServerErrorMessage,
                    serializationService = serializationService
                )
            ).addTo(compositeDisposable)
    }

    fun <T, Error : IErrorHandling> Single<T>.execute(
        result: MutableLiveData<IResult<T>>,
        error: Error
    ) {
        this.compose(getIOLoadingTransformers(result))
            .subscribe({
                result.value = Result.success(it)
            }, error)
    }
}