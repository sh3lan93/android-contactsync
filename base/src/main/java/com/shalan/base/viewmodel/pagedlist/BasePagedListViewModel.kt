package com.shalan.base.viewmodel.pagedlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.shalan.base.models.IPaginatedModel
import com.shalan.base.network.error.NetworkErrorHandlingImp
import com.shalan.base.services.SchedulerService
import com.shalan.base.services.SerializationService
import com.shalan.base.states.IResult
import com.shalan.base.states.Result
import com.shalan.base.viewmodel.PlainViewModel
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.functions.Consumer
import io.reactivex.rxjava3.kotlin.addTo

/**
 * Created by Mohamed Shalan on 6/2/20.
 */


abstract class BasePagedListViewModel<T : IPaginatedModel>(
    schedulerService: SchedulerService,
    serializationService: SerializationService,
    ioExceptionMessage: String,
    socketTimeoutExceptionMessage: String,
    internalServerErrorMessage: String
) : PlainViewModel(
    schedulerService = schedulerService,
    serializationService = serializationService,
    ioExceptionMessage = ioExceptionMessage,
    socketTimeoutExceptionMessage = socketTimeoutExceptionMessage,
    internalServerErrorMessage = internalServerErrorMessage
) {

    var refreshData: Boolean = false

    var page: Int = 1
        protected set

    protected val dataList: MutableLiveData<IResult<T>> by lazy {
        MutableLiveData<IResult<T>>()
    }

    val dataList_: LiveData<IResult<T>>
        get() = dataList

    protected val loadMoreEvent: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>(false)
    }

    val loadMoreEvent_: LiveData<Boolean>
        get() = loadMoreEvent

    fun enableLoadMore() {
        loadMoreEvent.value = true
    }

    override fun startLogic() {
        super.startLogic()
        if (refreshData || dataList.value?.fetchData() == null) {
            loadDataFromScratch(page).execute(dataList)
            refreshData = false
        }
    }

    fun resetPageNumber() {
        page = 1
    }

    open fun getNextPage() {
        if (dataList.value?.fetchData()?.shouldPaginate() == true)
            loadMoreData(++page).executeLoadMore()
    }

    abstract fun loadDataFromScratch(page: Int): Single<T>

    abstract fun loadMoreData(page: Int): Single<T>


    open fun Single<T>.executeLoadMore() {
        this.doOnSubscribe {
            dataList.postValue(Result.loading())
        }.compose(getIo())
            .subscribe(
                {
                    dataList.value = Result.success(it)
                    loadMoreEvent.value = false
                }, NetworkErrorHandlingImp(
                    result = dataList,
                    ioExceptionMessage = ioExceptionMessage,
                    socketExceptionMessage = socketTimeoutExceptionMessage,
                    loadMoreEvent = loadMoreEvent,
                    internalServerErrorExceptionMessage = internalServerErrorMessage,
                    serializationService = serializationService
                )
            ).addTo(compositeDisposable)
    }
}
