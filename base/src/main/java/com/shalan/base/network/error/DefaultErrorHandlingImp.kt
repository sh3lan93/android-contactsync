package com.shalan.base.network.error

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.shalan.base.network.Constants
import com.shalan.base.services.SerializationService
import com.shalan.base.states.IResult
import com.shalan.base.states.Result
import okhttp3.Headers
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException

/**
 * Created by Mohamed Shalan on 6/1/20.
 */


abstract class DefaultErrorHandlingImp<T>(
    private val result: MutableLiveData<IResult<T>>,
    private val loadMoreEvent: MutableLiveData<Boolean>? = null,
    protected val serializationService: SerializationService
) : IErrorHandling {

    companion object {
        val TAG = DefaultErrorHandlingImp::class.java.simpleName
    }

    override fun accept(t: Throwable?) {
        Log.e(TAG, "accept: ${t?.localizedMessage}")
        t?.let {
            loadMoreEvent?.value = false
            when (it) {
                is HttpException -> handleHttpException(it)
                is IOException -> result.postValue(Result.error(getIoExceptionMessage()))
                is SocketTimeoutException -> result.postValue(
                    Result.error(getSocketTimeExceptionMessage())
                )
                else -> result.postValue(Result.error(it.localizedMessage))
            }
        }
    }

    private fun handleHttpException(exception: HttpException) {
        val message: String?
        when (exception.code()) {
            Constants.UNAUTHORIZED_CODE -> message = handleUnAuthorizationException(
                exception.response()?.errorBody()?.string(), exception.response()?.headers()
            )
            Constants.FORBIDDEN_CODE -> message =
                handleForbiddenStatus(exception.response()?.errorBody()?.string())
            Constants.INTERNAL_SERVER_ERRO_CODE -> message = handleInternalServerError()
            Constants.NOT_FOUND_CODE -> message =
                handleNotFoundError(exception.response()?.errorBody()?.string())
            else -> message = extractErrorMessagesIfAny(exception.response()?.errorBody()?.string())
        }
        result.value = Result.error(message)
    }

    abstract fun getIoExceptionMessage(): String

    abstract fun getSocketTimeExceptionMessage(): String

    abstract fun extractErrorMessagesIfAny(errorBody: String?): String?

    abstract fun handleUnAuthorizationException(errorString: String?, headers: Headers?): String?

    abstract fun handleForbiddenStatus(errorBody: String?): String?

    abstract fun handleInternalServerError(): String?

    abstract fun handleNotFoundError(errorBody: String?): String?

}
