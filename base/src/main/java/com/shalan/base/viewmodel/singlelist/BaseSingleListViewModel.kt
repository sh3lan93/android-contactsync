package com.shalan.base.viewmodel.singlelist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.shalan.base.services.SchedulerService
import com.shalan.base.services.SerializationService
import com.shalan.base.states.IResult
import com.shalan.base.viewmodel.PlainViewModel
import io.reactivex.rxjava3.core.Single

/**
 * Created by Mohamed Shalan on 6/10/20.
 */


abstract class BaseSingleListViewModel<T> @JvmOverloads constructor(
    schedulerService: SchedulerService,
    serializationService: SerializationService,
    ioExceptionMessage: String = "",
    socketTimeoutExceptionMessage: String = "",
    internalServerErrorMessage: String = ""
) : PlainViewModel(
    schedulerService = schedulerService,
    serializationService = serializationService,
    ioExceptionMessage = ioExceptionMessage,
    socketTimeoutExceptionMessage = socketTimeoutExceptionMessage,
    internalServerErrorMessage = internalServerErrorMessage
) {

    var refreshData: Boolean = false

    protected val dataResult: MutableLiveData<IResult<List<T>>> by lazy {
        MutableLiveData<IResult<List<T>>>()
    }

    val data_: LiveData<IResult<List<T>>>
        get() = dataResult

    override fun startLogic() {
        super.startLogic()
        if (refreshData || dataResult.value?.fetchData() == null) {
            loadData().execute(dataResult)
            refreshData = false
        }
    }

    abstract fun loadData(): Single<List<T>>

}
