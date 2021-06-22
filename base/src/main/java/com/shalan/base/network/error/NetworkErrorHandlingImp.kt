package com.shalan.base.network.error

import androidx.lifecycle.MutableLiveData
import com.shalan.base.network.Constants
import com.shalan.base.services.SerializationService
import com.shalan.base.states.IResult
import okhttp3.Headers

/**
 * Created by Mohamed Shalan on 6/22/20.
 */


class NetworkErrorHandlingImp<T>(
    result: MutableLiveData<IResult<T>>,
    loadMoreEvent: MutableLiveData<Boolean>? = null,
    private val ioExceptionMessage: String,
    private val socketExceptionMessage: String,
    private val internalServerErrorExceptionMessage: String? = null,
    serializationService: SerializationService
) : DefaultErrorHandlingImp<T>(result, loadMoreEvent, serializationService) {


    override fun getIoExceptionMessage(): String = ioExceptionMessage

    override fun getSocketTimeExceptionMessage(): String = socketExceptionMessage

    override fun extractErrorMessagesIfAny(errorBody: String?): String? = errorBody?.let {
        serializationService.deserialize(it, Error::class.java)?.message
    }

    override fun handleUnAuthorizationException(errorString: String?, headers: Headers?): String? {
        if (headers != null && headers.get(Constants.AUTHORIZATION_HEADER) != null) {
            TODO("Not yet implemented")
        } else {
            errorString?.let {
                return serializationService.deserialize(it, Error::class.java)?.message
            }
        }
        return null
    }

    override fun handleForbiddenStatus(errorBody: String?): String? = errorBody?.let {
        return serializationService.deserialize(it, Error::class.java)?.message
    }

    override fun handleInternalServerError(): String? = internalServerErrorExceptionMessage

    override fun handleNotFoundError(errorBody: String?): String? = errorBody?.let {
        serializationService.deserialize(it, Error::class.java)?.message
    }
}
